package com.drone.api.drone.controller;

import com.drone.api.common.model.ResponseDto;
import com.drone.api.drone.model.Drone;
import com.drone.api.drone.model.DroneLoadDto;
import com.drone.api.drone.service.DispatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "API to dispatch drones",
        description = "This API provides the capability to dispatch drones with medications")
@RestController
@Validated
@RequestMapping("/drone")
public class DispatchController {

    private final DispatchService dispatchService;

    public DispatchController(DispatchService dispatchService) {
        this.dispatchService = dispatchService;
    }

    @Operation(summary = "Register Drone")
    @PostMapping(value = "register")
    public ResponseEntity<?> register(@Valid @RequestBody Drone drone) {
        ResponseDto responseDTO = dispatchService.register(drone);
        if (responseDTO.getData() != null) {
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.addMeta("message", "unable to register drone");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Load Drone")
    @PostMapping(value = "load")
    public ResponseEntity<?> load(@RequestBody DroneLoadDto droneLoadDto) {
        ResponseDto responseDTO = dispatchService.load(droneLoadDto);
        if (responseDTO.getData() != null) {
            return ResponseEntity.ok(responseDTO);
        }
        responseDTO.addMeta("message", "unable to load drone");
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Get availability List")
    @GetMapping(value = "available-list")
    public ResponseEntity<?> getAvailableDrones(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }

    @Operation(summary = "Check Load of Drone By Id")
    @GetMapping(value = "{id}/check-load")
    public ResponseEntity<?> checkLoad(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }

    @Operation(summary = "Check Battery of Drone By Id")
    @GetMapping(value = "{id}/check-battery")
    public ResponseEntity<?> checkBattery(@PathVariable Long id) {

        return new ResponseEntity<>(null);
    }
}
