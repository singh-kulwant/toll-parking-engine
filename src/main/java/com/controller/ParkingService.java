package com.controller;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pricing.models.Ticket;
import com.slot.models.ParkingSlot;
import com.slot.models.StandardSlot;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

@Service
public class ParkingService {

	public ParkingSlot getSlotType(Vehicle vehicle) {

		if (vehicle.getType().equals(VehicleType.GASOLINE)) {
			return StandardSlot.getSlotInstance();
		}
		return null;

	}

	public Ticket getSlotType(String licence) {

		Optional<Vehicle> vehicle = StandardSlot.getSlotInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getLicence())).findAny();

		if (vehicle.isPresent())
			return StandardSlot.getSlotInstance().unparkVehicle(vehicle.get());

		return null;

	}

}
