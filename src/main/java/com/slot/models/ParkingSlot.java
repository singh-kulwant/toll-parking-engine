package com.slot.models;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.pricing.models.PricingPolicy;
import com.pricing.models.ParkingTicket;
import com.vehicle.models.Vehicle;

public abstract class ParkingSlot {

	protected int freeSlots;
	protected int occupiedSlots;
	protected int slotCapacity;

	protected PricingPolicy pricingPolicy;
	protected HashMap<Vehicle, Integer> parkedVehicles;

	public abstract String parkVehicle(Vehicle vehicle);

	public ParkingTicket unparkVehicle(Vehicle vehicle) {

		if (parkedVehicles.containsKey(vehicle)) {
			parkedVehicles.remove(vehicle);
			freeSlots++;
			occupiedSlots--;

			ParkingTicket ticket = vehicle.getTicket();
			ticket.setExitTime(LocalDateTime.now());

			int hours = ticket.getExitTime().getHour() - ticket.getEntryTime().getHour();
			int mins = (ticket.getExitTime().getMinute() - ticket.getEntryTime().getMinute());
			long duration = hours + (mins / 60);
			ticket.setParkingDuration(duration);

			ticket.setBillAmount(this.pricingPolicy.generateBill(ticket.getParkingDuration()));

			return ticket;
		}
		return null;

	}

	public HashMap<Vehicle, Integer> parkedVehicles() {
		return parkedVehicles;
	}

	public int getFreeSlots() {
		return freeSlots;
	}

	public int getOccupiedSlots() {
		return occupiedSlots;
	}

	public int getSlotCapacity() {
		return slotCapacity;
	}

}
