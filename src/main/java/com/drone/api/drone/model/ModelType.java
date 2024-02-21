package com.drone.api.drone.model;



public enum ModelType {
    LightWeight("LightWeight"),
    Middleweight("Middleweight"),
    CruiserWeight("CruiserWeight"),
    HeavyWeight("HeavyWeight");

    private final String value;

    ModelType(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}

