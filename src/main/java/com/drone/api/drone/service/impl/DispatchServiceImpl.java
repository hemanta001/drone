package com.drone.api.drone.service.impl;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.dao.DroneRepository;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.service.DispatchService;
import org.springframework.stereotype.Service;

@Service
public class DispatchServiceImpl implements DispatchService {
    private final DroneRepository droneRepository;

    public DispatchServiceImpl(DroneRepository droneRepository) {
        this.droneRepository = droneRepository;
    }

    @Override
    public ResponseDto register(Drone drone) {
        return null;
    }

    @Override
    public ResponseDto load(DroneLoadDto droneLoadDto) {
        return null;
    }
}
