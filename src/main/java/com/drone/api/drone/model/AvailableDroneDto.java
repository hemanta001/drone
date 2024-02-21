package com.drone.api.drone.model;

import com.drone.api.validator.enumvalidator.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AvailableDroneDto {
    private Long id;

    private String serialNumber;

    private String modelType;

    private double weightLimitInGram;

    private double batteryCapacity;

    private String state;

    private double weightLoadedInGram;

}
