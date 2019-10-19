package com.parking.api;

import java.util.HashMap;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pricing.models.ParkingTicket;
import com.slot.models.HeavyElectricSlot;
import com.slot.models.LightElectricSlot;
import com.slot.models.ParkingSlot;
import com.slot.models.StandardSlot;
import com.vehicle.models.Vehicle;
import com.vehicle.models.VehicleType;

/**
 * Parking service performs operations on parking slots
 * 
 * @author root
 *
 */
@Service
public class ParkingService {

	public ParkingSlot getSlotType(Vehicle vehicle) {

		if (vehicle.getVehicleType().equals(VehicleType.GASOLINE)) {
			return StandardSlot.getInstance();
		}
		if (vehicle.getVehicleType().equals(VehicleType.LIGHT_ELECTRIC)) {
			return LightElectricSlot.getInstance();
		}
		if (vehicle.getVehicleType().equals(VehicleType.HEAVY_ELECTRIC)) {
			return HeavyElectricSlot.getInstance();
		}
		return null;

	}

	public ParkingTicket unparkVehicle(String licence) {

		Optional<Vehicle> vehicle = Optional.empty();

		vehicle = StandardSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getVehicleRegistration())).findAny();

		if (vehicle.isPresent())
			return StandardSlot.getInstance().unparkVehicle(vehicle.get());

		vehicle = LightElectricSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getVehicleRegistration())).findAny();

		if (vehicle.isPresent())
			return LightElectricSlot.getInstance().unparkVehicle(vehicle.get());

		vehicle = HeavyElectricSlot.getInstance().parkedVehicles().keySet().stream()
				.filter(v -> licence.equalsIgnoreCase(v.getVehicleRegistration())).findAny();

		if (vehicle.isPresent())
			return HeavyElectricSlot.getInstance().unparkVehicle(vehicle.get());

		return null;

	}

	public HashMap<String, HashMap<String, Integer>> parkingStatus() {

		HashMap<String, HashMap<String, Integer>> parkingSlots = new HashMap<String, HashMap<String, Integer>>();

		parkingSlots.put("Standard Parking Slot", getSlotStatus(StandardSlot.getInstance()));
		parkingSlots.put("20KW Electric Parking Slot", getSlotStatus(LightElectricSlot.getInstance()));
		parkingSlots.put("50KW Electric Parking Slot", getSlotStatus(HeavyElectricSlot.getInstance()));
		return parkingSlots;
	}

	public HashMap<String, Integer> getSlotStatus(ParkingSlot parkingSlot) {

		HashMap<String, Integer> slot = new HashMap<String, Integer>();
		slot.put("Free Slots", parkingSlot.getFreeSlots());
		slot.put("Occupied Slots", parkingSlot.getOccupiedSlots());
		slot.put("Slot Capacity", parkingSlot.getSlotCapacity());

		return slot;
	}

}
