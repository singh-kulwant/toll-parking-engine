package com.vehicle.models;

import com.pricing.models.ParkingTicket;

public class Vehicle {

	private String licence;
	private VehicleType type;
	private ParkingTicket ticket;

	public void setTicket() {
		this.ticket = new ParkingTicket(this.licence);
	}

	public Vehicle() {
	}

	public Vehicle(String licence, VehicleType type) {
		this.licence = licence;
		this.type = type;
	}

	public String getLicence() {
		return licence;
	}

	public VehicleType getType() {
		return type;
	}

	public ParkingTicket getTicket() {
		return ticket;
	}

}
