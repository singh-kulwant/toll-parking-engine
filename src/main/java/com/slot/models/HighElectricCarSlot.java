package com.slot.models;

import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.vehicle.models.Vehicle;

public class HighElectricCarSlot implements ParkingSlot {

	public HighElectricCarSlot(int capacity, PricingPolicy pricingPolicy) {
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
		// TODO Auto-generated method stu
		return null;
	}

	@Override
	public String unparkVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ParkingSlot getAvailableSlots() {
		// TODO Auto-generated method stub
		return null;
	}

}
