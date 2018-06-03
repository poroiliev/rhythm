package com.rhythm.usercrossings.repositories;

import com.rhythm.usercrossings.models.Crossing;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CrossingRepository extends CrudRepository<Crossing, Long> {

    @Query("select c from Crossing c join Person p on p.id = c.person.id where p.passportId = ?1")
    List<Crossing> findByPassportId(Long passportId);

}
