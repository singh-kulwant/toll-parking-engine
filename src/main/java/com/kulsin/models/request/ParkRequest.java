package com.kulsin.models.request;

import lombok.Data;

@Data
public class ParkRequest {

    private String plateNumber;
    private String vehicleType;
    private String color;
    private String brand;
    private String slotNumber;

}
