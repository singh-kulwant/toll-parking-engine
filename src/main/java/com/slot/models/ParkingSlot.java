package com.slot.models;

import java.util.HashMap;

import com.pricing.models.Ticket;
import com.vehicle.models.Vehicle;

public interface ParkingSlot {

	public String parkVehicle(Vehicle vehicle);

	public Ticket unparkVehicle(Vehicle vehicle);

	public HashMap<Vehicle, Integer> parkedVehicles();

}
