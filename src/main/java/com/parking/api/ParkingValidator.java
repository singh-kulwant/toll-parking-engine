package com.parking.api;

import java.util.function.Predicate;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.slot.models.HeavyElectricSlot;
import com.slot.models.LightElectricSlot;
import com.slot.models.StandardSlot;
import com.vehicle.models.Vehicle;

@Component
public class ParkingValidator {

	public Boolean validate(Vehicle vehicle) {
		if (vehicle != null && ObjectUtils.isNotEmpty(vehicle.getVehicleRegistration())
				&& ObjectUtils.isNotEmpty(vehicle.getVehicleType()))
			return true;
		else
			return false;
	}

	public Boolean checkIfAlreadyPresent(Vehicle vehicle) {

		Predicate<Vehicle> licenceMatch = v -> vehicle.getVehicleRegistration()
				.equalsIgnoreCase(v.getVehicleRegistration());

		if (StandardSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(licenceMatch)
				|| LightElectricSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(licenceMatch)
				|| HeavyElectricSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(licenceMatch)) {

			return true;
		} else {
			return false;
		}
	}
}
