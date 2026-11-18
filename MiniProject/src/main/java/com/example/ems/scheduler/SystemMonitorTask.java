package com.example.ems.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SystemMonitorTask {

    private static final Logger log = LoggerFactory.getLogger(SystemMonitorTask.class);

    @Scheduled(fixedRate = 30000)
    public void logSystemRunning() {
        log.info("System running");
    }
}
