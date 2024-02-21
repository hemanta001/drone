package com.drone.api.drone.dao;

import com.drone.api.drone.model.Drone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT NEW com.drone.api.drone.model.AvailableDroneDto(d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state, SUM(med.weightInGram))" +
            "FROM Drone d " +
            "INNER JOIN Medication med ON d.id = med.droneId " +
            "WHERE d.state = 'IDLE' " +
            "  AND d.batteryCapacity >= 25 " +
            "GROUP BY d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state " +
            "HAVING d.weightLimitInGram > SUM(med.weightInGram)")
    List<Object> getAvailableDrones();

    @Query("select d.batteryCapacity from Drone d where d.id=:id")
    Double getBatteryPercentage(Long id);

}
