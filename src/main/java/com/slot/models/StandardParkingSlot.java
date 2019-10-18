package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.GasolineSedanCar;
import com.vehicle.models.Vehicle;

public class StandardParkingSlot implements ParkingSlot {

	public StandardParkingSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.capacity = capacity;
		this.freeSlot = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	private int capacity;
	private int freeSlot;
	private int occupiedSlots;
	private HashMap<Vehicle, Integer> parkedVehicles;
	private PricingPolicy pricingPolicy;

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlot != 0 && vehicle instanceof GasolineSedanCar) {
			parkedVehicles.put(vehicle, freeSlot);
			freeSlot--;
			occupiedSlots++;

			return "Vehicle parked at slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle instanceof GasolineSedanCar) {
				return "Sorry! Parking slots are full.";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}
	}

	@Override
	public String unparkVehicle(Vehicle vehicle) {
		if (parkedVehicles.containsKey(vehicle)) {
			parkedVehicles.remove(vehicle);
			freeSlot++;
			occupiedSlots--;

			return "Vehicle unparked. Kindly pay";
		} else {
			return "This vehicle is not parked at parking";
		}
	}

	@Override
	public ParkingSlot getAvailableSlots() {
		// TODO Auto-generated method stub
		return null;
	}

}
