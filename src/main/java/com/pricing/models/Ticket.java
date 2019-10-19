package com.pricing.models;

import java.time.LocalDateTime;

public class Ticket {

	private LocalDateTime entryTime;

	private LocalDateTime exitTime;

	private long duration;

	private long totalAmount;

	private String licence;

	public LocalDateTime getEntryTime() {
		return entryTime;
	}

	public LocalDateTime getExitTime() {
		return exitTime;
	}

	public long getDuration() {
		return duration;
	}

	public long getTotalAmount() {
		return totalAmount;
	}

	public String getLicence() {
		return licence;
	}

	public void setEntryTime(LocalDateTime entryTime) {
		this.entryTime = entryTime;
	}

	public void setExitTime(LocalDateTime exitTime) {
		this.exitTime = exitTime;
	}

	public void setDuration(long duration) {
		this.duration = duration;
	}

	public void setTotalAmount(long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

	public Ticket(String licence) {
		this.entryTime = LocalDateTime.now();
		this.licence = licence;
	}

	Ticket() {

	}

}
