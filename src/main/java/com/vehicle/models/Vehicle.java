package com.vehicle.models;

import com.pricing.models.Ticket;

public class Vehicle {

	private String licence;
	private VehicleType type;
	private Ticket ticket;

	public void setTicket() {
		this.ticket = new Ticket(this.licence);
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

	public Ticket getTicket() {
		return ticket;
	}

}
