package com.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pricing.models.Ticket;
import com.slot.models.HeavyElectricSlot;
import com.slot.models.LightElectricSlot;
import com.slot.models.ParkingSlot;
import com.slot.models.StandardSlot;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

@Service
public class ParkingService {

	public String parkVehicle(Vehicle vehicle) {
		ParkingSlot parkingSlot = getSlotType(vehicle);
		return parkingSlot.parkVehicle(vehicle);
	}

	public ParkingSlot getSlotType(Vehicle vehicle) {

		if (vehicle.getType().equals(VehicleType.GASOLINE)) {
			return StandardSlot.getInstance();
		}
		return null;

	}

	public Ticket unparkVehicle(String licence) {

		Optional<Vehicle> vehicle = Optional.empty();

		vehicle = StandardSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getLicence())).findAny();

		if (vehicle.isPresent())
			return StandardSlot.getInstance().unparkVehicle(vehicle.get());

		vehicle = LightElectricSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getLicence())).findAny();

		if (vehicle.isPresent())
			return LightElectricSlot.getInstance().unparkVehicle(vehicle.get());

		vehicle = HeavyElectricSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getLicence())).findAny();

		if (vehicle.isPresent())
			return HeavyElectricSlot.getInstance().unparkVehicle(vehicle.get());

		return null;

	}

	public List<ParkingSlot> parkingStatus() {

		List<ParkingSlot> list = new ArrayList<ParkingSlot>();
		list.add(StandardSlot.getInstance());
		list.add(LightElectricSlot.getInstance());
		list.add(HeavyElectricSlot.getInstance());
		return list;
	}

}
