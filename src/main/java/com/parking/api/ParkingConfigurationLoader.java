package com.parking.api;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.pricing.models.FixedHourlyBilling;
import com.pricing.models.HourlyBilling;
import com.slot.models.HeavyElectricSlot;
import com.slot.models.LightElectricSlot;
import com.slot.models.StandardSlot;

@Configuration
@PropertySource("classpath:application.yml")
@ConfigurationProperties(prefix = "parking")
public class ParkingConfigurationLoader {

	@Value(value = "${standardCapacity}")
	public int standardCapacity;

	@Value(value = "${lightElectricCapacity}")
	public int lightElectricCapacity;

	@Value(value = "${heavyElectricCapacity}")
	public int heavyElectricCapacity;

	@Value(value = "${hourlyBillingRate}")
	public Long hourlyBillingRate;

	@Value(value = "${fixedHourlyBillingRate}")
	public Long fixedHourlyBillingRate;

	@Value(value = "${fixedHourlyFixedCharge}")
	public Long fixedHourlyFixedCharge;

	@Bean
	public void init() {

		/* Initializing Billing rates and fixed charges for various Pricing policies */
		HourlyBilling.rate = hourlyBillingRate;
		FixedHourlyBilling.rate = fixedHourlyBillingRate;
		FixedHourlyBilling.fixedCharge = fixedHourlyFixedCharge;

		/*
		 * Initializing instances of Parking slots, with parking slot capacity and
		 * Pricing policy
		 */
		StandardSlot.initStandardSlotInstance(standardCapacity, new HourlyBilling());
		LightElectricSlot.initLightElectricSlotInstance(lightElectricCapacity, new FixedHourlyBilling());
		HeavyElectricSlot.initHeavyElectricSlotInstance(heavyElectricCapacity, new FixedHourlyBilling());

	}

}
