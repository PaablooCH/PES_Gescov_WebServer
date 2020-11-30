package com.gescov.webserver;

import com.gescov.webserver.service.ContagionService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ScheduledTasks {

    private final ContagionService contagionService;

    public ScheduledTasks(ContagionService contagionService) {
        this.contagionService = contagionService;
    }

    @Scheduled(fixedRate = 60000)
    public void deleteContagionWithEndContagionNull() {
        LocalDate date = LocalDate.now();
        contagionService.deleteContagion(date);
    }
}
