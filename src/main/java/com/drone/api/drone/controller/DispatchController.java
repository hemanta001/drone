package com.drone.api.drone.controller;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.service.DispatchService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("drone")
public class DispatchController {

    private final DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @PostMapping(value = "register")
    public ResponseEntity<?> register(@RequestBody Drone drone) {
        ResponseDto responseDTO = dispatchService.register(drone);
        if (responseDTO.getData() != null) {
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.addMeta("message", "unable to register drone");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "load")
    public ResponseEntity<?> load(@RequestBody DroneLoadDto droneLoadDto) {
        ResponseDto responseDTO = dispatchService.load(droneLoadDto);
        if (responseDTO.getData() != null) {
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.addMeta("message", "unable to load drone");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "available-list")
    public ResponseEntity<?> getAvailableDrones(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }

    @GetMapping(value = "{id}/check-load")
    public ResponseEntity<?> checkLoad(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }

    @GetMapping(value = "{id}/check-battery")
    public ResponseEntity<?> checkBattery(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }
}
