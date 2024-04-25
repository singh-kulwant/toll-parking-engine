package com.kulsin.models.pricing;

public interface PricingPolicy {

	public Long generateBill(Long duration);
}
