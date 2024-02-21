package com.drone.api.drone.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Drone {
    @Id
    private Long id;

    @Column(length = 100)
    @NotBlank(message = "Serial Number cannot be blank")
    @Size(min = 1, max = 100, message = "Serial Number must not exceed 100 characters")
    private String serialNumber;

    @Column
    private ModelType modelType;

    @Column
    @Max(value = 500, message = "Value must be less than or equal to 500")
    private double weightInGram;

    @Column
    @Max(value = 100, message = "Value must be between 0 and 100")
    @Min(value = 0, message = "Value must be between 0 and 100")
    private double batteryCapacity;

    @Column
    private State state;
}
