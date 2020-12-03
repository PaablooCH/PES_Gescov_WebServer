package com.gescov.webserver;

import com.gescov.webserver.service.ContagionService;
import com.gescov.webserver.service.SchoolService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduledTasks {

    private final ContagionService contagionService;

    private final SchoolService schoolService;

    public ScheduledTasks(ContagionService contagionService, SchoolService schoolService) {
        this.contagionService = contagionService;
        this.schoolService = schoolService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone="Europe/Spain")
    public void deleteContagionWithEndContagionNull() {
        LocalDate date = LocalDate.now();
        contagionService.deleteContagion(date);
    }

    @Scheduled(fixedRate = 60000/*172800000*/)
    public void doRegister() {
        LocalDate date = LocalDate.now();
        schoolService.doRegister(date);
    }
}
