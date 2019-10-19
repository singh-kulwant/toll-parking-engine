package com.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.models.ParkingTicket;
import com.vehicle.models.Vehicle;

@RestController
@RequestMapping("/parking")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	@ResponseBody
	public String parkVehicle(@RequestBody Vehicle vehicle) {
		return parkingService.parkVehicle(vehicle);
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
