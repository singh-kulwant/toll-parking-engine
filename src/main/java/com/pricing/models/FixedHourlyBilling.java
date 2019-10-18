package com.pricing.models;

public class FixedHourlyBilling implements PricingPolicy {

	private Double rate;

	private int parkingHours;

	private Double fixedCharge;

	@Override
	public Double generateBill() {
		return fixedCharge + (parkingHours * rate);
	}

}
