package com.slot.models;

import com.vehicle.models.Vehicle;

public interface ParkingSlot {

	public String parkVehicle(Vehicle vehicle);

	public String unparkVehicle(Vehicle vehicle);

	public ParkingSlot getAvailableSlots();
}
