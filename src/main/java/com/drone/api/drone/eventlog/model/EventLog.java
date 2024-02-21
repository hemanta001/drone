package com.drone.api.drone.eventlog.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EventLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    Long droneId;

    @Column
    String droneSerialNumber;

    @Column
    double batteryCapacity;

    @Column
    LocalDateTime loggedDateTime;

}
