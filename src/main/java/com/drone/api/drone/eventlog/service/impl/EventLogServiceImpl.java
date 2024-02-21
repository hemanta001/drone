package com.drone.api.drone.eventlog.service.impl;

import com.drone.api.drone.eventlog.dao.EventLogRepository;
import com.drone.api.drone.eventlog.model.EventLog;
import com.drone.api.drone.eventlog.service.EventLogService;
import org.springframework.stereotype.Service;

@Service
public class EventLogServiceImpl implements EventLogService {
    private final EventLogRepository eventLogRepository;

    public EventLogServiceImpl(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }

    @Override
    public void createEventLog(EventLog eventLog) {
        this.eventLogRepository.save(eventLog);
    }
}
