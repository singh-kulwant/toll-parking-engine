package com.pricing.models;

public interface PricingPolicy {

	public Long generateBill(Long duration);
}
