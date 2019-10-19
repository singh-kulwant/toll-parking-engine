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
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlots != 0 && vehicle.getType().equals(VehicleType.LIGHT_ELECTRIC)) {
			vehicle.setTicket();
			parkedVehicles.put(vehicle, freeSlots);
			freeSlots--;
			occupiedSlots++;

			return "Vehicle parked at 20KW Electric Parking Slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getType().equals(VehicleType.LIGHT_ELECTRIC)) {
				return "Sorry! 20KW Electric Parking slots are full";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}
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
