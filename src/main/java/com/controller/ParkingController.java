package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.models.Ticket;
import com.slot.models.ParkingSlot;
import com.vehicle.models.Vehicle;

@RestController
@RequestMapping("/park")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	@RequestMapping(value = "/vehicle", method = RequestMethod.POST)
	@ResponseBody
	public String parkVehicle(@RequestBody Vehicle vehicle) {
		ParkingSlot parkingSlot = parkingService.getSlotType(vehicle);
		return parkingSlot.parkVehicle(vehicle);
	}

	@RequestMapping(value = "/vehicle/{licence}", method = RequestMethod.GET)
	@ResponseBody
	public Ticket unparkVehicle(@PathVariable String licence) {
		return parkingService.getSlotType(licence);
	}

}
