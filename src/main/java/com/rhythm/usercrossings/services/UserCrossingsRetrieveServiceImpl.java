package com.rhythm.usercrossings.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rhythm.usercrossings.config.ApplicationConfiguration;
import com.rhythm.usercrossings.models.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class UserCrossingsRetrieveServiceImpl implements UserCrossingsRetrieveService {

    private static final String URL_STATIC_PART = "/data/crossings/";
    private static final String PROTOCOL = "http://";

    private String requestUrl;

    private RestTemplate restTemplate;
    private ApplicationConfiguration configuration;
    private ObjectMapper objectMapper;

    @Autowired
    public UserCrossingsRetrieveServiceImpl(RestTemplate restTemplate,
                                            ApplicationConfiguration configuration,
                                            ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.configuration = configuration;
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        requestUrl = PROTOCOL + configuration.getCountryHost() + URL_STATIC_PART + configuration.getTimestamp();
    }

    @Override
    public List<Person> retrieveData() {
        List<Person> people = retrieveFromCountryUrl();
        if (CollectionUtils.isEmpty(people)) {
            log.debug("Received empty list from {}", requestUrl);
            return Collections.emptyList();
        }

        return people;
    }

    private List<Person> retrieveFromCountryUrl() {
        byte[] forObject = restTemplate.getForObject(requestUrl, byte[].class);

        List<Person> result = null;
        try {
            result = objectMapper.readValue(forObject, new TypeReference<List<Person>>() {
            });
        } catch (IOException e) {
            log.error("Could not deserialize result - [{}]", e);
        }

        return result;
    }
}
