package com.kulsin.models;

import lombok.Getter;

@Getter
public enum VehicleType {

    GASOLINE("gasoline"),
    LIGHT_ELECTRIC("light_electric"),
    HEAVY_ELECTRIC("heavy_electric");

    private final String name;

    VehicleType(String description) {
        this.name = description;
    }

    public static VehicleType customValueOf(String value) {
        for (VehicleType type : values()) {
            if (type.name.equalsIgnoreCase(value)) {
                return type;
            }
        }
        return GASOLINE;
    }

}
