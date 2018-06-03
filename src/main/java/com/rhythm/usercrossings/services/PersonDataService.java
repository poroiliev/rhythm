package com.rhythm.usercrossings.services;

import com.rhythm.usercrossings.dto.CrossingResultDTO;
import com.rhythm.usercrossings.models.Person;
import com.rhythm.usercrossings.services.base.CrudService;

import java.util.List;

public interface PersonDataService extends CrudService<Person, Long> {

    boolean checkIfExists(Person person);

    Person findByNamesAndPassport(Person person);

    List<CrossingResultDTO> findCrossingsByPassportId(Long passportId);
}
