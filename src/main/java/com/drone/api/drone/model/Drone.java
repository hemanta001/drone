package com.drone.api.drone.model;

import com.drone.api.drone.validator.CustomValidation;
import com.drone.api.validator.enumvalidator.ValueOfEnum;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

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
    @Column(length = 100,unique = true)
    @NotBlank(message = "Serial Number cannot be blank")
    @Size(min = 1, max = 100, message = "Serial Number must not exceed 100 characters")
    private String serialNumber;

    @Schema(description = "The model type of drone", defaultValue = "LightWeight")
    @NotBlank(message = "model type is required")
    @ValueOfEnum(enumClass = ModelType.class, message = "must be of any from value from LightWeight, Middleweight, CruiserWeight, HeavyWeight")
    private String modelType;

    @Schema(description = "Weight in Gram")
    @Column
    @Max(value = 500, message = "Value must be less than or equal to 500")
    private double weightLimitInGram;

    @Schema(description = "Battery Percentage")
    @Column
    @Max(value = 100, message = "Value must be between 0 and 100")
    @Min(value = 0, message = "Value must be between 0 and 100")
    @Valid
    private double batteryCapacity;

    @Schema(description = "State of drone", defaultValue = "IDLE")
    @ValueOfEnum(enumClass = State.class, message = "must be of any from value from IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING")
    @Hidden
    private String state;

    @OneToMany(mappedBy = "droneId", fetch = FetchType.LAZY)
    @Hidden
    private List<Medication> medicationList;

    public String getState() {
        return this.batteryCapacity < 25.00 ? State.IDLE.getValue() : State.LOADING.getValue();
    }
}
