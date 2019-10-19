package com.pricing.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class ParkingTicket {

	private LocalDateTime entryTime;

	private LocalDateTime exitTime;

	private long parkingDuration;

	private long billAmount;

	private String vehicleRegistration;

	
	public ParkingTicket(String vehicleRegistration) {
		this.entryTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		this.vehicleRegistration = vehicleRegistration;
	}

}
