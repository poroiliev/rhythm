package com.rhythm.usercrossings.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "rhythm.users")
public class ApplicationConfiguration {

    private String countryHost;
    private Long timestamp;
    private Integer schedulerIntervalHrs;

}
