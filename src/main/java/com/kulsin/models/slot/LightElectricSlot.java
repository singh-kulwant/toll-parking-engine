package com.kulsin.models.slot;

import java.util.HashMap;

import com.kulsin.models.pricing.PricingPolicy;
import com.kulsin.models.vehicle.Vehicle;
import com.kulsin.models.vehicle.VehicleType;

/**
 * Parking slot class for 20KW electric cars
 * 
 * @author root
 *
 */
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
