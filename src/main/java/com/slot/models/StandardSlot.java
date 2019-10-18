package com.slot.models;

import java.util.HashMap;

import com.pricing.models.HourlyBilling;
import com.pricing.models.PricingPolicy;
import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class StandardSlot implements ParkingSlot {

	public static int standardParkingCapacity;
	
	private final int capacity;
	private int freeSlot;
	private int occupiedSlots;
	private HashMap<Vehicle, Integer> parkedVehicles;

	private PricingPolicy pricingPolicy;

	private static StandardSlot standardParkingSlot;

	private StandardSlot(int capacity) {
		this.pricingPolicy = new HourlyBilling();
		this.freeSlot = capacity;
		this.capacity = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlot != 0 && vehicle.getType().equals(VehicleType.GASOLINE)) {
			vehicle.setTicket();
			parkedVehicles.put(vehicle, freeSlot);
			freeSlot--;
			occupiedSlots++;

			return "Vehicle parked at slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getType().equals(VehicleType.GASOLINE)) {
				return "Sorry! Parking slots are full.";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}
	}

	@Override
	public Ticket unparkVehicle(Vehicle vehicle) {
		if (parkedVehicles.containsKey(vehicle)) {
			parkedVehicles.remove(vehicle);
			freeSlot++;
			occupiedSlots--;

			Ticket ticket = vehicle.getTicket();
			ticket.setDuration(System.currentTimeMillis() - ticket.getEntryTime());
			ticket.setTotalAmount(this.pricingPolicy.generateBill(ticket.getDuration()));

			return ticket;
		}
		return null;
	}

	public static ParkingSlot getSlotInstance() {
		if (standardParkingSlot == null) {
			standardParkingSlot = new StandardSlot(standardParkingCapacity);
		}
		return standardParkingSlot;
	}

	@Override
	public HashMap<Vehicle, Integer> parkedVehicles() {
		return parkedVehicles;
	}

}
