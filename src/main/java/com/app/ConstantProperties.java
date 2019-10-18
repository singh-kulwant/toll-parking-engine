package com.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pricing.models.HourlyBilling;
import com.slot.models.StandardSlot;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "parking")
public class ConstantProperties {



	@Value(value = "${capacity:5}")
	public int capacity;

	@Value(value = "${hourlyBillingRate}")
	public Long hourlyBillingRate;

	@Bean
	public void init() {
		StandardSlot.standardParkingCapacity = capacity;
		HourlyBilling.rate = hourlyBillingRate;
	}

}
