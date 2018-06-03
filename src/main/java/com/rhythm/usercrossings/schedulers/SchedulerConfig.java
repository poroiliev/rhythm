package com.rhythm.usercrossings.schedulers;

import com.rhythm.usercrossings.config.ApplicationConfiguration;
import com.rhythm.usercrossings.schedulers.jobs.CountryDataJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SchedulerConfig {

    @Bean
    public JobDetail jobDetail() {
        return JobBuilder.newJob()
                .ofType(CountryDataJob.class)
                .withDescription("Country crossings job detail")
                .storeDurably(true)
                .build();
    }

    @Bean
    public Trigger trigger(JobDetail jobDetail, ApplicationConfiguration configuration) {
        return TriggerBuilder.newTrigger().forJob(jobDetail())
                .withIdentity("Country crossings trigger")
                .startNow()
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .repeatForever()
                        .withIntervalInHours(configuration.getSchedulerIntervalHrs()))
                .forJob(jobDetail)
                .build();
    }

}
