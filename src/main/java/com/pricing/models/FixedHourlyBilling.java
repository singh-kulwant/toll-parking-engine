package com.pricing.models;

import org.springframework.beans.factory.annotation.Value;

public class FixedHourlyBilling implements PricingPolicy {

	private Long fixedCharge;

	@Value(value = "${hourlyBillingRate}")
	private Long rate;

	@Override
	public Long generateBill(Long duration) {
		return fixedCharge + (rate * duration);
	}

}
