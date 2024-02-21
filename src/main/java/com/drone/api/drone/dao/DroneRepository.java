package com.drone.api.drone.dao;

import com.drone.api.drone.model.Drone;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT NEW com.drone.api.drone.model.DroneDto(d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state, SUM(med.weightInGram))" +
            "FROM Drone d " +
            "INNER JOIN Medication med ON d.id = med.droneId " +
            "WHERE d.state = 'LOADING' " +
            "  AND d.batteryCapacity >= 25 " +
            "GROUP BY d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state " +
            "HAVING d.weightLimitInGram > SUM(med.weightInGram)")
    List<Object> getAvailableDrones();

    @Query("SELECT NEW com.drone.api.drone.model.DroneDto(d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state, SUM(med.weightInGram)) " +
            "FROM Drone d " +
            "INNER JOIN Medication med ON d.id = med.droneId " +
            "GROUP BY d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state ")
    List<Object> getSummaryOfDrones(Pageable pageable);

    @Query("SELECT coalesce(CASE WHEN d.state = 'LOADING' " +
            "AND d.batteryCapacity >= 25 AND " +
            "d.weightLimitInGram >= coalesce(SUM(coalesce(med.weightInGram,0.00)),0.00)+:weightOfMedicationsLoad THEN true ELSE false END,true ) as eligibility " +
            "FROM Drone d " +
            "INNER JOIN Medication med ON d.id = med.droneId " +
            "WHERE d.id=:id " +
            "GROUP BY d.id, d.serialNumber, d.modelType, d.weightLimitInGram, d.batteryCapacity, d.state ")
    boolean checkDroneLoadingEligibility(Long id, double weightOfMedicationsLoad);

    @Query("select d.batteryCapacity from Drone d where d.id=:id")
    Double getBatteryPercentage(Long id);

}
