package com.vehicle.models;

import com.pricing.models.ParkingTicket;

import lombok.Data;

/**
 * Vehicle details stored in parking slots
 * 
 * @author root
 *
 */
@Data
public class Vehicle {

	private VehicleType vehicleType;

	private String vehicleRegistration;

	private ParkingTicket parkingTicket;

	public void setTicket() {
		this.parkingTicket = new ParkingTicket(this.vehicleRegistration);
	}

}
