package com.slot.models;

import java.util.List;

import com.vehicle.models.Vehicle;

public class Slot {

	private int capacity;
	private int freeSlot;
	private int occupiedSlot;
	private List<Vehicle> vehicles;
	
	public Slot(int capacity, int freeSlot, int occupiedSlot, List<Vehicle> vehicles) {
		super();
		this.capacity = capacity;
		this.freeSlot = freeSlot;
		this.occupiedSlot = occupiedSlot;
		this.vehicles = vehicles;
	}
	
	
}
