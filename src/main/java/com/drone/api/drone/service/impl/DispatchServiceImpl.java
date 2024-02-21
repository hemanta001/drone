package com.drone.api.drone.service.impl;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.dao.DroneRepository;
import com.drone.api.drone.dao.MedicationRepository;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.service.DispatchService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DispatchServiceImpl implements DispatchService {
    private final DroneRepository droneRepository;
    private final MedicationRepository medicationRepository;

    public DispatchServiceImpl(DroneRepository droneRepository, MedicationRepository medicationRepository) {
        this.droneRepository = droneRepository;
        this.medicationRepository = medicationRepository;
    }

    @Override
    public ResponseDto register(Drone drone) {
        drone = this.droneRepository.save(drone);
        drone.setMedicationList(this.medicationRepository.saveAll(drone.getMedicationList()));
        return new ResponseDto(drone);
    }

    @Override
    public ResponseDto load(DroneLoadDto droneLoadDto) {
        return new ResponseDto(this.medicationRepository.saveAll(droneLoadDto.getMedicationList()));
    }
}
