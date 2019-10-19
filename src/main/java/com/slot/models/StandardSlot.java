package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

/**
 * Parking slot class for standard gasoline cars
 * 
 * @author root
 *
 */
public class StandardSlot extends ParkingSlot {

	private static StandardSlot standardParkingSlot;

	private StandardSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.vehicleType = VehicleType.GASOLINE;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	public static ParkingSlot getInstance() {
		return standardParkingSlot;
	}

	public static ParkingSlot initStandardSlotInstance(int capacity, PricingPolicy pricingPolicy) {
		if (standardParkingSlot == null) {
			standardParkingSlot = new StandardSlot(capacity, pricingPolicy);
		}
		return standardParkingSlot;
	}

}