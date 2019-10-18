package com.pricing.models;

public class HourlyBilling implements PricingPolicy {

	public static Long rate;

	@Override
	public Long generateBill(Long duration) {
		return rate * duration;
	}

}
