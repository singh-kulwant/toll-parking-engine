package com.pricing.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Parking ticket given to customer for payment
 * 
 * @author root
 *
 */
@Data
public class ParkingTicket {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime entryTime;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
	private LocalDateTime exitTime;

	private long parkingDuration;

	private long billAmount;

	private String vehicleRegistration;

	public ParkingTicket(String vehicleRegistration) {
		/* Hard-coding entryTime for billing calculation demonstration purpose */
		this.entryTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
		this.vehicleRegistration = vehicleRegistration;
	}

}
