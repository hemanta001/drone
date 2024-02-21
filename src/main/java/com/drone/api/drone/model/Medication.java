package com.drone.api.drone.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Medication Load")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Max(value = 500, message = "Value must be less than or equal to 500")
    private Double weightInGram;

    @Column(nullable = false)
    @Schema(description = "Medication Name")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$", message = "Invalid Name. Only letters, numbers, '-', and '_' are allowed.")
    private String name;

    @Column(nullable = false)
    @Schema(description = "Medication Code")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid Code. Only uppercase letters, numbers, and underscores are allowed.")
    private String code;

    @Column
    @Schema(description = "Image Key")
    private String imageKey;

    @Schema(description = "id of drone")
    @Hidden
    private Long droneId;
}
