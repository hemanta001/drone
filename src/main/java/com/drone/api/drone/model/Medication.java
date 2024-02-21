package com.drone.api.drone.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Medication {
    @Id
    private Long id;

    @Column
    @Max(value = 500, message = "Value must be less than or equal to 500")
    private double weightInGram;

    @Schema(description = "Medication Name")
    private String name;

    @Schema(description = "Image Key")
    private String imageKey;
}
