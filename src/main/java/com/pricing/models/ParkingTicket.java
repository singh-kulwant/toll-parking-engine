package com.pricing.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Parking ticket given to customer for payment
 * 
 * @author root
 *
 */
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

	/**
	 * @return the entryTime
	 */
	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	/**
	 * @return the exitTime
	 */
	public LocalDateTime getExitTime() {
		return exitTime;
	}

	/**
	 * @return the parkingDuration
	 */
	public long getParkingDuration() {
		return parkingDuration;
	}

	/**
	 * @return the billAmount
	 */
	public long getBillAmount() {
		return billAmount;
	}

	/**
	 * @return the vehicleRegistration
	 */
	public String getVehicleRegistration() {
		return vehicleRegistration;
	}

	/**
	 * @param entryTime the entryTime to set
	 */
	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * @param exitTime the exitTime to set
	 */
	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	/**
	 * @param parkingDuration the parkingDuration to set
	 */
	public void setParkingDuration(long parkingDuration) {
		this.parkingDuration = parkingDuration;
	}

	/**
	 * @param billAmount the billAmount to set
	 */
	public void setBillAmount(long billAmount) {
		this.billAmount = billAmount;
	}

	/**
	 * @param vehicleRegistration the vehicleRegistration to set
	 */
	public void setVehicleRegistration(String vehicleRegistration) {
		this.vehicleRegistration = vehicleRegistration;
	}

}
