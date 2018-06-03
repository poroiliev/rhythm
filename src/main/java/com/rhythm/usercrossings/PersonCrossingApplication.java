package com.rhythm.usercrossings;

import com.rhythm.usercrossings.config.ApplicationConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ApplicationConfiguration.class})
@Slf4j
public class PersonCrossingApplication {

    public static void main(String[] args) {
        SpringApplication.run(PersonCrossingApplication.class, args);
        log.info("PersonCrossingApplication started");
    }
}
