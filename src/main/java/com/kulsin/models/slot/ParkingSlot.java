package com.kulsin.models.slot;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.kulsin.models.pricing.ParkingTicket;
import com.kulsin.models.pricing.PricingPolicy;
import com.kulsin.models.vehicle.Vehicle;
import com.kulsin.models.vehicle.VehicleType;

public abstract class ParkingSlot {

	protected int freeSlots;
	protected int occupiedSlots;
	protected int slotCapacity;
	protected VehicleType vehicleType;

	protected PricingPolicy pricingPolicy;
	protected HashMap<Vehicle, Integer> parkedVehicles;

	public String parkVehicle(Vehicle vehicle) {

		if (freeSlots != 0 && vehicle.getVehicleType().equals(this.vehicleType)) {
			//vehicle.setParkingTicket();
			parkedVehicles.put(vehicle, freeSlots);
			freeSlots--;
			occupiedSlots++;

			return "Vehicle parked at " + this.vehicleType + " Parking Slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getVehicleType().equals(this.vehicleType)) {
				return "Sorry! " + this.vehicleType + " Parking Slots are full";
			} else {
				return "Wrong parking slot for vehicle.";
			}

		}

	}

	public ParkingTicket unparkVehicle(Vehicle vehicle) {

		if (parkedVehicles.containsKey(vehicle)) {
			parkedVehicles.remove(vehicle);
			freeSlots++;
			occupiedSlots--;

			ParkingTicket ticket = vehicle.getParkingTicket();
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
