package com.slot.models;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import com.pricing.models.FixedHourlyBilling;
import com.pricing.models.PricingPolicy;
import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;

public class LightElectricSlot implements ParkingSlot {

	private LightElectricSlot(int capacity) {
		this.pricingPolicy = new FixedHourlyBilling();
		this.capacity = capacity;
		this.freeSlot = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	private static LightElectricSlot midElectricSlot;

	@Value(value = "${midElectricParkingCapacity:2}")
	private static int capacity;
	private int freeSlot;
	private int occupiedSlots;
	private HashMap<Vehicle, Integer> parkedVehicles;
	private PricingPolicy pricingPolicy;

	@Override
	public String parkVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket unparkVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ParkingSlot getSlotInstance() {
		if (midElectricSlot == null) {
			midElectricSlot = new LightElectricSlot(capacity);
		}
		return midElectricSlot;
	}
	
	@Override
	public HashMap<Vehicle, Integer> parkedVehicles() {
		return parkedVehicles;
	}

}
