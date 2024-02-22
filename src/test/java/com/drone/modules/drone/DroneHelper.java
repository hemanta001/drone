package com.drone.modules.drone;

import com.drone.api.drone.model.Drone;


public class DroneHelper {
    private static final Drone droneRequest = prepareDroneRegisterRequest();
    private static final Drone droneResponse = prepareDroneRegisterResponse();

    private static Drone prepareDroneRegisterRequest() {
        Drone drone = new Drone();
        drone.setBatteryCapacity(75.00);
        drone.setSerialNumber("123abcd");
        drone.setWeightLimitInGram(500);
        drone.setModelType("LightWeight");
        return drone;
    }

    public static Drone getDroneRequest() {
        return droneRequest;
    }

    public static Drone getDroneResponse() {
        return droneResponse;
    }

    private static Drone prepareDroneRegisterResponse() {
        Drone drone = prepareDroneRegisterRequest();
        drone.setId(1L);
        return drone;
    }
}
