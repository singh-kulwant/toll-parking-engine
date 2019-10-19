package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class LightElectricSlot extends ParkingSlot {

	private static LightElectricSlot lightElectricParkingSlot;

	private LightElectricSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.vehicleType = VehicleType.LIGHT_ELECTRIC;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	public static ParkingSlot getInstance() {
		return lightElectricParkingSlot;
	}

	public static ParkingSlot initLightElectricSlotInstance(int capacity, PricingPolicy pricingPolicy) {
		if (lightElectricParkingSlot == null) {
			lightElectricParkingSlot = new LightElectricSlot(capacity, pricingPolicy);
		}
		return lightElectricParkingSlot;
	}

}
