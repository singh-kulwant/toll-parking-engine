package com.pricing.models;

/**
 * Charges customer for parking using fixed and hourly rates
 * 
 * @author root
 *
 */
public class FixedHourlyBilling implements PricingPolicy {

	public static Long fixedCharge;

	public static Long rate;

	@Override
	public Long generateBill(Long duration) {
		return fixedCharge + (rate * duration);
	}

}
