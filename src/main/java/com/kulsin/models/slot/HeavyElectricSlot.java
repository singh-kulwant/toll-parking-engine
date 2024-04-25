package com.kulsin.models.slot;

import java.util.HashMap;

import com.kulsin.models.pricing.PricingPolicy;
import com.kulsin.models.vehicle.Vehicle;
import com.kulsin.models.vehicle.VehicleType;

/**
 * Parking slot class for 50KW electric cars
 * 
 * @author root
 *
 */
public class HeavyElectricSlot extends ParkingSlot {

	private static HeavyElectricSlot heavyElectricParkingSlot;

	private HeavyElectricSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.vehicleType = VehicleType.HEAVY_ELECTRIC;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	public static ParkingSlot getInstance() {
		return heavyElectricParkingSlot;
	}

	public static ParkingSlot initHeavyElectricSlotInstance(int capacity, PricingPolicy pricingPolicy) {
		if (heavyElectricParkingSlot == null) {
			heavyElectricParkingSlot = new HeavyElectricSlot(capacity, pricingPolicy);
		}
		return heavyElectricParkingSlot;
	}

}