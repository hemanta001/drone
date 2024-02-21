package com.drone.api.drone.eventlog.scheduler;

import com.drone.api.drone.dao.DroneRepository;
import com.drone.api.drone.eventlog.model.EventLog;
import com.drone.api.drone.eventlog.service.async.EventLogAsyncService;
import com.drone.api.drone.model.DroneDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@Component
public class EventLogScheduler {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogScheduler.class);

    private final DroneRepository droneRepository;

    private final EventLogAsyncService eventLogAsyncService;

    public EventLogScheduler(DroneRepository droneRepository, EventLogAsyncService eventLogAsyncService) {
        this.droneRepository = droneRepository;
        this.eventLogAsyncService = eventLogAsyncService;
    }

    @Scheduled(fixedRate = 10, timeUnit = TimeUnit.SECONDS)
    public void processDroneEventLogs() {
        LOGGER.info("Executing periodic task...");
        long totalCount = this.droneRepository.count();
        int limit = 8;
        int pages = (int) Math.ceil((double) totalCount / limit);
        IntStream.range(0, pages)
                .forEach(value -> {
                    Pageable pageable = PageRequest.of(value, limit);
                    List<DroneDto> droneList = new ObjectMapper().convertValue(this.droneRepository.getSummaryOfDrones(pageable), new TypeReference<>() {
                    });
                    List<CompletableFuture<Void>> futures = droneList
                            .stream()
                            .map(drone -> this.eventLogAsyncService.createEventLog(new EventLog(null, drone.getId(), drone.getSerialNumber(), drone.getBatteryCapacity(), LocalDateTime.now())))
                            .toList();
                    CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

                    // You can add additional logic to be executed after all CompletableFutures are completed
                    allOf.thenRun(() -> LOGGER.info("Logging completed for page {} limit {}", value, limit));

                    // Block and wait for all tasks to complete
                    allOf.join();
                });


    }
}
