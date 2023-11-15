package com.videorental.jobs;

import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

@Configuration
class QuartzConfig {

    private static final String WEEKLY_JOB_CREATOR_CRON = "0 0 12 ? * THU";

    @Bean
    SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {

        SpringBeanJobFactory springBeanJobFactory = new SpringBeanJobFactory();

        springBeanJobFactory.setApplicationContext(applicationContext);

        return springBeanJobFactory;
    }

    @Bean
    public JobDetail weeklySaleCreationJobDetails() {

        return JobBuilder.newJob(MovieJob.class)
                .withIdentity("movieJob")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger weeklySaleCreationTrigger() {

        return TriggerBuilder.newTrigger()
                .forJob(weeklySaleCreationJobDetails())
                .withIdentity("weeklySaleCreationTrigger")
                .withSchedule(CronScheduleBuilder.cronSchedule(WEEKLY_JOB_CREATOR_CRON))
                .build();
    }
}
