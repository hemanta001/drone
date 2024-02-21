package com.drone.api.drone.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class DroneDto {
    private Long id;

    private String serialNumber;

    private String modelType;

    private double weightLimitInGram;

    private double batteryCapacity;

    private String state;

    private double weightLoadedInGram;

}
