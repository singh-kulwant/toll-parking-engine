package com.slot.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class StandardSlot implements ParkingSlot, Serializable {

	private int freeSlots;
	private int occupiedSlots;
	private final int slotCapacity;

	private PricingPolicy pricingPolicy;
	private HashMap<Vehicle, Integer> parkedVehicles;

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

	@Override
	public Ticket unparkVehicle(Vehicle vehicle) {
		if (parkedVehicles.containsKey(vehicle)) {
			parkedVehicles.remove(vehicle);
			freeSlots++;
			occupiedSlots--;

			Ticket ticket = vehicle.getTicket();
			ticket.setExitTime(LocalDateTime.now());

			int hours = ticket.getExitTime().getHour() - ticket.getEntryTime().getHour();
			int mins = (ticket.getExitTime().getMinute() - ticket.getEntryTime().getMinute());
			long duration = hours + (mins / 60);
			ticket.setDuration(duration);

			ticket.setTotalAmount(this.pricingPolicy.generateBill(ticket.getDuration()));

			return ticket;
		}
		return null;
	}

	@Override
	public HashMap<Vehicle, Integer> parkedVehicles() {
		return parkedVehicles;
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