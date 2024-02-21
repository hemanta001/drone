package com.drone.api.drone.eventlog.service;

import com.drone.api.drone.eventlog.model.EventLog;

public interface EventLogService {
    void createEventLog(EventLog eventLog);
}
