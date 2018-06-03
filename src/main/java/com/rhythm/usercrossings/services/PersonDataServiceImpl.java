package com.rhythm.usercrossings.services;

import com.rhythm.usercrossings.dto.CrossingResultDTO;
import com.rhythm.usercrossings.models.Crossing;
import com.rhythm.usercrossings.models.Person;
import com.rhythm.usercrossings.repositories.CrossingRepository;
import com.rhythm.usercrossings.repositories.PersonRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
@Transactional
public class PersonDataServiceImpl implements PersonDataService {

    private PersonRepository personRepository;
    private CrossingRepository crossingRepository;

    @Autowired
    public PersonDataServiceImpl(PersonRepository personRepository, CrossingRepository crossingRepository) {
        this.personRepository = personRepository;
        this.crossingRepository = crossingRepository;
    }

    @Override
    public Person save(Person entity) {
        preventNullability(entity);

        Person person = personRepository.save(entity);
        if (!CollectionUtils.isEmpty(person.getCrossings())) {
            person.getCrossings().forEach(crossing -> crossing.setPerson(person));
            crossingRepository.saveAll(person.getCrossings());
        }

        return person;
    }

    @Override
    public List<Person> save(List<Person> entity) {
        return entity.stream()
                .filter(Objects::nonNull)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Person entity) {
        if (entity.getCrossings() != null) {
            crossingRepository.deleteAll(entity.getCrossings());
        }

        personRepository.delete(entity);
    }

    @Override
    public boolean checkIfExists(Person person) {
        preventNullProperties(person);

        return personRepository.existsByFirstNameAndLastNameAndPassportId(person.getFirstName(),
                person.getLastName(),
                person.getPassportId());
    }

    @Override
    public Person findByNamesAndPassport(Person person) {
        preventNullProperties(person);

        return personRepository.findByFirstNameAndLastNameAndPassportId(person.getFirstName(),
                person.getLastName(),
                person.getPassportId());
    }

    @Override
    public List<CrossingResultDTO> findCrossingsByPassportId(Long passportId) {
        List<Crossing> crossingList = crossingRepository.findByPassportId(passportId);

        if (CollectionUtils.isEmpty(crossingList)) {
            return Collections.emptyList();
        } else {
            return crossingList.stream().map(CrossingResultDTO::from).collect(Collectors.toList());
        }
    }

    private void preventNullProperties(Person person) {
        preventNullability(person);

        if (Stream.of(person.getFirstName(), person.getLastName(), person.getPassportId()).anyMatch(Objects::isNull)) {
            log.error("Invalid data for user [{}]", person);
            throw new IllegalArgumentException();
        }
    }

    private void preventNullability(Person entity) {
        if (entity == null) {
            log.debug("Can not save null to DB");
            throw new IllegalArgumentException();
        }
    }
}
