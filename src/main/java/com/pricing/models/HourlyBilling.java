package com.pricing.models;

public class HourlyBilling implements PricingPolicy {

	private Double rate;

	private int parkingHours;

	@Override
	public Double generateBill() {
		return rate * parkingHours;
	}

}
