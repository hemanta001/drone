package com.drone.api.drone.eventlog.service.async;


import com.drone.api.drone.eventlog.model.EventLog;
import com.drone.api.drone.eventlog.service.EventLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class EventLogAsyncService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogAsyncService.class);
    private final EventLogService eventLogService;

    public EventLogAsyncService(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @Async("createLog")
    public CompletableFuture<Void> createEventLog(EventLog eventLog) {
        try {
            Thread.sleep(1000);
            LOGGER.info("creating log for drone id {}", eventLog.getDroneId());
            this.eventLogService.createEventLog(eventLog);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage());
        }
        return CompletableFuture.completedFuture(null);
    }
}
