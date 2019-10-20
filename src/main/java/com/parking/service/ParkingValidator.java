package com.parking.service;

import java.util.function.Predicate;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Component;

import com.slot.models.HeavyElectricSlot;
import com.slot.models.LightElectricSlot;
import com.slot.models.StandardSlot;
import com.vehicle.models.Vehicle;

/**
 * 
 * Performs validations on API input request
 * 
 * @author root
 *
 */
@Component
public class ParkingValidator {

	public Boolean validate(Vehicle vehicle) {
		if (vehicle != null && ObjectUtils.isNotEmpty(vehicle.getVehicleRegistration())
				&& ObjectUtils.isNotEmpty(vehicle.getVehicleType()))
			return true;
		else
			return false;
	}

	public boolean checkIfAlreadyParked(String vehicleRegistration) {

		Predicate<Vehicle> registrationMatch = v -> vehicleRegistration.equalsIgnoreCase(v.getVehicleRegistration());

		if (StandardSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(registrationMatch)
				|| LightElectricSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(registrationMatch)
				|| HeavyElectricSlot.getInstance().parkedVehicles().keySet().stream().anyMatch(registrationMatch)) {

			return true;
		} else {
			return false;
		}
	}
}
