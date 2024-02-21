package com.drone.api.drone.dao;

import com.drone.api.drone.model.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicationRepository extends JpaRepository<Medication, Long> {
    List<Medication> findAllByDroneId(Long id);
}
