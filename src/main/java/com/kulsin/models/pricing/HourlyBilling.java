package com.kulsin.models.pricing;

/**
 * 
 * define pricing policy to charges customer for parking on hourly basis
 * 
 * @author root
 *
 */
public class HourlyBilling implements PricingPolicy {

	public static Long rate;

	@Override
	public Long generateBill(Long duration) {
		return rate * duration;
	}

}
