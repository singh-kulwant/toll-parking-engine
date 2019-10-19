package com.pricing.models;

public class FixedHourlyBilling implements PricingPolicy {

	public static Long fixedCharge;

	public static Long rate;

	@Override
	public Long generateBill(Long duration) {
		return fixedCharge + (rate * duration);
	}

}
