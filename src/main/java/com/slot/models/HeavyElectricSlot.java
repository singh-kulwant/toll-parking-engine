package com.slot.models;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import com.pricing.models.FixedHourlyBilling;
import com.pricing.models.PricingPolicy;
import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;

public class HeavyElectricSlot implements ParkingSlot {

	private HeavyElectricSlot(int capacity) {
		this.pricingPolicy = new FixedHourlyBilling();
		this.capacity = capacity;
		this.freeSlot = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}
	
	private static HeavyElectricSlot highElectricSlot;

	@Value(value = "${highElectricParkingCapacity:2}")
	private static int capacity;
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
	public Ticket unparkVehicle(Vehicle vehicle) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ParkingSlot getSlotInstance() {
		if (highElectricSlot == null) {
			highElectricSlot = new HeavyElectricSlot(capacity);
		}
		return highElectricSlot;
	}
	
	@Override
	public HashMap<Vehicle, Integer> parkedVehicles() {
		return parkedVehicles;
	}

}
