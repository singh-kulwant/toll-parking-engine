package com.parking.api;

import java.util.HashMap;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.models.ParkingTicket;
import com.slot.models.ParkingSlot;
import com.vehicle.models.Vehicle;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	private static final String BAD_REQUEST = "Bad request with malformed syntax";

	private static final String INTERNAL_ERROR = "Internal error occured due to Parking Slot instance";

	private static final String VEHICLE_ALREADY_PARKED = " vehicle already parked in parking slot, Malformed request";

	@Autowired
	private ParkingService parkingService;

	@Autowired
	private ParkingValidator parkingValidator;

	@Autowired
	private ApiExceptionHandler exceptionHandler;

	@PostMapping(value = "/vehicle")
	@ResponseBody
	public ResponseEntity<Object> parkVehicle(@RequestBody Vehicle vehicle) {

		if (!parkingValidator.validate(vehicle))
			return exceptionHandler.buildErrorResponseEntity(HttpStatus.BAD_REQUEST, BAD_REQUEST);

		if (parkingValidator.checkIfAlreadyPresent(vehicle)) {
			return exceptionHandler.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
					vehicle.getVehicleRegistration() + VEHICLE_ALREADY_PARKED);
		} else {

			ParkingSlot parkingSlot = parkingService.getSlotType(vehicle);

			if (ObjectUtils.isEmpty(parkingSlot))
				return exceptionHandler.buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR);

			return new ResponseEntity<>(parkingSlot.parkVehicle(vehicle), HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/vehicle/{vehicleRegistration}", method = RequestMethod.DELETE)
	@ResponseBody
	public ParkingTicket unparkVehicle(@PathVariable String vehicleRegistration) {
		return parkingService.unparkVehicle(vehicleRegistration);
	}

	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public HashMap<String, HashMap<String, Integer>> parkingStatus() {
		return parkingService.parkingStatus();
	}

}