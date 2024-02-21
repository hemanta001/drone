package com.drone.api.drone.service;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.model.Medication;

import java.util.List;

public interface DispatchService {
    ResponseDto register(Drone drone);

    ResponseDto load(DroneLoadDto droneLoadDto);

    ResponseDto getLoadedMedications(Long droneId);

    ResponseDto getAvailableDrones();

    ResponseDto getBatteryPercentage(Long id);
}
