package com.drone.api.drone.model;

import lombok.Getter;

public enum State {
    IDLE("IDLE"),
    LOADING("LOADING"),
    LOADED("LOADED"),
    DELIVERING("DELIVERING"),
    DELIVERED("DELIVERED"),
    RETURNING("RETURNING");

    private final String value;

    State(String value) {
        this.value = value;
    }
    public String getValue() {
        return this.value;
    }
}



