package com.drone.api.drone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

    private double weightInGram;

    private String name;

    private String imageKey;
}
