package com.pricing.models;

public class Ticket {

	public long getEntryTime() {
		return entryTime;
	}

	public long getExitTime() {
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

	private long entryTime;

	public void setEntryTime(long entryTime) {
		this.entryTime = entryTime;
	}

	public void setExitTime(long exitTime) {
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

	private long exitTime;

	private long duration;

	private long totalAmount;

	private String licence;

	public Ticket(String licence) {
		this.entryTime = System.currentTimeMillis();
		this.licence = licence;
	}
	
	Ticket() {
		
	}

}
