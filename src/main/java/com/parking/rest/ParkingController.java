package com.parking.rest;

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

import com.parking.config.ApiExceptionHandler;
import com.parking.config.ApiMessage;
import com.parking.service.ParkingService;
import com.parking.service.ParkingValidator;
import com.pricing.models.ParkingTicket;
import com.slot.models.ParkingSlot;
import com.vehicle.models.Vehicle;

/**
 * 
 * Main parking controller class
 * 
 * @author root
 *
 */
@RestController
@RequestMapping("/parking")
public class ParkingController {

	private static final String NOT_FOUND = " not found due to technical error";

	private static final String NOT_PARKED = " not parked in any parking slot";

	private static final String BAD_REQUEST = "Bad request with malformed syntax";

	private static final String INTERNAL_ERROR = "Internal server error occured in Parking Slot instances";

	private static final String VEHICLE_ALREADY_PARKED = " vehicle already parked in parking slot, Malformed request";

	private static final String INCORRECT_URI = "Incorrect uri(add vehicle registration in uri) or change request method to park vehicle";

	@Autowired
	private ParkingService parkingService;

	@Autowired
	private ParkingValidator parkingValidator;

	@Autowired
	private ApiExceptionHandler exceptionHandler;

	/**
	 * 
	 * Parks vehicle and update free and reserved parking slots
	 * 
	 * @param vehicle
	 * @return
	 */
	@PostMapping(value = "/vehicle")
	@ResponseBody
	public ResponseEntity<Object> parkVehicle(@RequestBody Vehicle vehicle) {

		if (!parkingValidator.validate(vehicle))
			return exceptionHandler.buildErrorResponseEntity(HttpStatus.BAD_REQUEST, BAD_REQUEST);

		if (parkingValidator.checkIfAlreadyParked(vehicle.getVehicleRegistration())) {
			return exceptionHandler.buildErrorResponseEntity(HttpStatus.BAD_REQUEST,
					vehicle.getVehicleRegistration() + VEHICLE_ALREADY_PARKED);
		} else {

			ParkingSlot parkingSlot = parkingService.getSlotType(vehicle);

			if (ObjectUtils.isEmpty(parkingSlot))
				return exceptionHandler.buildErrorResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR);

			return new ResponseEntity<>(new ApiMessage(HttpStatus.OK, parkingSlot.parkVehicle(vehicle)), HttpStatus.OK);
		}

	}

	/**
	 * 
	 * Unpark vehicle from parking and generate parking ticket
	 * 
	 * @param vehicleRegistration
	 * @return
	 */
	@RequestMapping(value = "/vehicle/{vehicleRegistration}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> unparkVehicle(@PathVariable(required = true) String vehicleRegistration) {

		if (parkingValidator.checkIfAlreadyParked(vehicleRegistration)) {
			ParkingTicket response = parkingService.unparkVehicle(vehicleRegistration);

			if (ObjectUtils.isNotEmpty(response)) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ApiMessage(HttpStatus.NOT_FOUND, vehicleRegistration + NOT_FOUND),
						HttpStatus.NOT_FOUND);
			}

		} else {
			return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST, vehicleRegistration + NOT_PARKED),
					HttpStatus.BAD_REQUEST);
		}
	}

	/**
	 * 
	 * Shows current status of all Parking Slots like free slots, occupied slots and
	 * slot capacity
	 * 
	 * @return
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Object> parkingStatus() {

		HashMap<String, HashMap<String, Integer>> response = parkingService.parkingStatus();

		if (ObjectUtils.isNotEmpty(response)) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiMessage(HttpStatus.INTERNAL_SERVER_ERROR, INTERNAL_ERROR),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@RequestMapping(value = "/vehicle/", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity<Object> incorrectUriHandler() {

		return new ResponseEntity<>(new ApiMessage(HttpStatus.BAD_REQUEST, INCORRECT_URI), HttpStatus.BAD_REQUEST);
	}

}