package com.rhythm.usercrossings.repositories;

import com.rhythm.usercrossings.models.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Long> {

    boolean existsByFirstNameAndLastNameAndPassportId(String firstName, String lastName, Long passportId);

    Person findByFirstNameAndLastNameAndPassportId(String firstName, String lastName, Long passportId);
}
