package com.rhythm.usercrossings.controllers;

import com.rhythm.usercrossings.dto.CrossingResultDTO;
import com.rhythm.usercrossings.services.PersonDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "person/crossings")
@Slf4j
public class PersonCrossingsController {

    private PersonDataService personDataService;

    @Autowired
    public PersonCrossingsController(PersonDataService personDataService) {
        this.personDataService = personDataService;
    }

    @GetMapping(path = "{passportId}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> findCrossingsByPassport(@PathVariable Long passportId) {
        log.debug("Incoming request for passport with id: [{}]", passportId);

        List<CrossingResultDTO> crossingResult = personDataService.findCrossingsByPassportId(passportId);
        return new ResponseEntity<>(crossingResult, HttpStatus.OK);
    }

}
