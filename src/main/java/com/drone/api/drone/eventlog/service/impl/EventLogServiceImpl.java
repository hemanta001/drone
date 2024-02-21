package com.drone.api.drone.eventlog.service.impl;

import com.drone.api.drone.eventlog.dao.EventLogRepository;
import com.drone.api.drone.eventlog.model.EventLog;
import com.drone.api.drone.eventlog.service.EventLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class EventLogServiceImpl implements EventLogService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EventLogServiceImpl.class);

    private final EventLogRepository eventLogRepository;

    public EventLogServiceImpl(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Override
    public void createEventLog(EventLog eventLog) {
        try {
            this.eventLogRepository.save(eventLog);
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }
}
