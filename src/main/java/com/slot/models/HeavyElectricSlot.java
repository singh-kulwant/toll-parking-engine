package com.slot.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;

import com.pricing.models.FixedHourlyBilling;
import com.pricing.models.PricingPolicy;
import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

public class HeavyElectricSlot implements ParkingSlot, Serializable {

	private int freeSlots;
	private int occupiedSlots;
	private final int slotCapacity;

	private PricingPolicy pricingPolicy;
	private HashMap<Vehicle, Integer> parkedVehicles;

	private static HeavyElectricSlot heavyElectricParkingSlot;

	private HeavyElectricSlot(int capacity, PricingPolicy pricingPolicy) {
		this.pricingPolicy = pricingPolicy;
		this.freeSlots = capacity;
		this.slotCapacity = capacity;
		this.occupiedSlots = 0;
		this.parkedVehicles = new HashMap<Vehicle, Integer>(capacity);
	}

	@Override
	public String parkVehicle(Vehicle vehicle) {
		if (freeSlots != 0 && vehicle.getType().equals(VehicleType.HEAVY_ELECTRIC)) {
			vehicle.setTicket();
			parkedVehicles.put(vehicle, freeSlots);
			freeSlots--;
			occupiedSlots++;

			return "Vehicle parked at 50KW Electric Parking Slot: " + parkedVehicles.get(vehicle);
		} else {
			if (vehicle.getType().equals(VehicleType.HEAVY_ELECTRIC)) {
				return "Sorry! 50KW Electric Parking slots are full";
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
		return heavyElectricParkingSlot;
	}

	public static ParkingSlot initHeavyElectricSlotInstance(int capacity, PricingPolicy pricingPolicy) {
		if (heavyElectricParkingSlot == null) {
			heavyElectricParkingSlot = new HeavyElectricSlot(capacity, pricingPolicy);
		}
		return heavyElectricParkingSlot;
	}

}