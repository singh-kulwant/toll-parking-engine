package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class HeavyElectricSlot extends ParkingSlot {

	private static HeavyElectricSlot heavyElectricParkingSlot;

	private HeavyElectricSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlots != 0 && vehicle.getType().equals(VehicleType.HEAVY_ELECTRIC)) {
			vehicle.setTicket();
			parkedVehicles.put(vehicle, freeSlots);
			freeSlots--;
			occupiedSlots++;

			return "Vehicle parked at 50KW Electric Parking Slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getType().equals(VehicleType.HEAVY_ELECTRIC)) {
				return "Sorry! 50KW Electric Parking slots are full";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}
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