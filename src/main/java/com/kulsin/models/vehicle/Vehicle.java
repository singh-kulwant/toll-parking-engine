package com.kulsin.models.vehicle;

import com.kulsin.models.pricing.ParkingTicket;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vehicle {

    private ParkingTicket parkingTicket;
    private String vehicleRegistration;
    private VehicleType vehicleType;

}
