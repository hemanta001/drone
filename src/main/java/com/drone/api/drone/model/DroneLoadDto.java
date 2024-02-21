package com.drone.api.drone.model;

import com.drone.api.drone.validator.CustomValidation;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;


@CustomValidation(message = "battery percentage should be greater than 25 " +
        "and load weight should not exceed weight limit")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DroneLoadDto {
    @Schema(description = "Drone Id")
    @NotNull
    Long droneId;

    @Schema(description = "List of Medications to be Loaded")
    @Size(min = 1, message = "Medication list should contain at least one element")
    List<Medication> medicationList;

    public List<Medication> getMedicationList() {
        return this.medicationList.stream()
                .peek(value -> value.setDroneId(this.droneId))
                .collect(Collectors.toList());
    }
}
