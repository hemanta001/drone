package com.drone.api.drone.model;

import com.drone.api.validator.enumvalidator.ValueOfEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Drone Detail Payload")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Serial Number Of Drone")
    @Column(length = 100)
    @NotBlank(message = "Serial Number cannot be blank")
    @Size(min = 1, max = 100, message = "Serial Number must not exceed 100 characters")
    private String serialNumber;

    @Schema(description = "The model type of drone")
    @NotBlank(message = "model type is required")
    @ValueOfEnum(enumClass = ModelType.class, message = "must be of any from value from LightWeight, Middleweight, CruiserWeight, HeavyWeight")
    private String modelType;

    @Schema(description = "Weight in Gram")
    @Column
    @Max(value = 500, message = "Value must be less than or equal to 500")
    private double weightInGram;

    @Schema(description = "Battery Percentage")
    @Column
    @Max(value = 100, message = "Value must be between 0 and 100")
    @Min(value = 0, message = "Value must be between 0 and 100")
    private double batteryCapacity;

    @Schema(description = "State of drone")
    @ValueOfEnum(enumClass = State.class, message = "must be of any from value from IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING")
    private String state;
}
