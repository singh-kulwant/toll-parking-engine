package com.kulsin.models.response;

import com.kulsin.models.VehicleType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ParkingStatus {

    private String slotNumber;
    private String brand;
    private String color;
    private String entryTime;
    private String plateNumber;
    private VehicleType vehicleType;

}