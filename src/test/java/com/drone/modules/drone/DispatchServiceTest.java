package com.drone.modules.drone;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.dao.DroneRepository;
import com.drone.api.drone.dao.MedicationRepository;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.service.DispatchService;
import com.drone.api.drone.service.impl.DispatchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(DispatchServiceTest.class)
public class DispatchServiceTest {

    @MockBean
    private DroneRepository droneRepository;

    @MockBean
    private MedicationRepository medicationRepository;
    private DispatchService dispatchService;


    @BeforeEach
    void setup() {
        dispatchService = new DispatchServiceImpl(droneRepository, medicationRepository);
    }

    @Test
    public void registerDroneTest() {
        Drone drone = DroneHelper.getDroneRequest();
        Drone expectedDrone = DroneHelper.getDroneResponse();
        when(this.droneRepository.save(drone)).thenReturn(expectedDrone);
        ResponseDto responseDto = this.dispatchService.register(drone);
        assertEquals(new ResponseDto(expectedDrone), responseDto);
    }
}
