package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class StandardSlot extends ParkingSlot {

	private static StandardSlot standardParkingSlot;

	private StandardSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlots != 0 && vehicle.getType().equals(VehicleType.GASOLINE)) {
			vehicle.setTicket();
			parkedVehicles.put(vehicle, freeSlots);
			freeSlots--;
			occupiedSlots++;

			return "Vehicle parked at Standard Parking Slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getType().equals(VehicleType.GASOLINE)) {
				return "Sorry! Standard Parking Slots are full";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}
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