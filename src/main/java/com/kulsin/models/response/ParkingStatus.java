package com.kulsin.models.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingStatus {

    private Integer slotNumber;
    private String brand;
    private String color;
    private String entryTime;
    private String plateNumber;
    private String vehicleType;

}