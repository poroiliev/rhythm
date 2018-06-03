package com.rhythm.usercrossings.schedulers.jobs;

import com.rhythm.usercrossings.models.Person;
import com.rhythm.usercrossings.services.PersonDataService;
import com.rhythm.usercrossings.services.UserCrossingsRetrieveService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@DisallowConcurrentExecution
public class CountryDataJob extends QuartzJobBean {

    private UserCrossingsRetrieveService retrieveService;
    private PersonDataService personDataService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        List<Person> people = retrieveService.retrieveData();

        Map<Boolean, List<Person>> oldUserStatus = people.stream().collect(Collectors.partitioningBy(personDataService::checkIfExists));

        processExistingPeople(oldUserStatus.get(Boolean.TRUE));
        processNewPeople(oldUserStatus.get(Boolean.FALSE));

    }

    @Autowired
    public void setRetrieveService(UserCrossingsRetrieveService retrieveService) {
        this.retrieveService = retrieveService;
    }

    @Autowired
    public void setPersonDataService(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    private void processExistingPeople(List<Person> people) {
        people.forEach(person -> {
            Person storedPerson = personDataService.findByNamesAndPassport(person);

            if (!CollectionUtils.isEmpty(person.getCrossings())) {
                if (!CollectionUtils.isEmpty(storedPerson.getCrossings())) {
                    person.getCrossings().removeAll(storedPerson.getCrossings());
                } else {
                    storedPerson.setCrossings(person.getCrossings());
                }
            }

            personDataService.save(storedPerson);

        });
    }

    private void processNewPeople(List<Person> people) {
        people.forEach(person -> {
            if (!CollectionUtils.isEmpty(person.getCrossings())) {
                person.getCrossings().forEach(crossing -> crossing.setPerson(person));
            }
        });

        personDataService.save(people);
    }
}
