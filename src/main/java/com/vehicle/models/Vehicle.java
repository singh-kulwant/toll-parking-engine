package com.vehicle.models;

import com.pricing.models.ParkingTicket;

/**
 * Vehicle details stored in parking slots
 * 
 * @author root
 *
 */
public class Vehicle {

	private VehicleType vehicleType;

	private String vehicleRegistration;

	private ParkingTicket parkingTicket;

	public void setParkingTicket() {
		this.parkingTicket = new ParkingTicket(this.vehicleRegistration);
	}

	/**
	 * @return the vehicleType
	 */
	public VehicleType getVehicleType() {
		return vehicleType;
	}

	/**
	 * @return the vehicleRegistration
	 */
	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	/**
	 * @return the parkingTicket
	 */
	public ParkingTicket getParkingTicket() {
		return parkingTicket;
	}

	/**
	 * @param vehicleType the vehicleType to set
	 */
	public void setVehicleType(VehicleType vehicleType) {
		this.vehicleType = vehicleType;
	}

	/**
	 * @param vehicleRegistration the vehicleRegistration to set
	 */
	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

	/**
	 * @param parkingTicket the parkingTicket to set
	 */
	public void setParkingTicket(ParkingTicket parkingTicket) {
		this.parkingTicket = parkingTicket;
	}

}
