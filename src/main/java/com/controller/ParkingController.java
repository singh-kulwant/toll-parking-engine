package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.models.Ticket;
import com.slot.models.ParkingSlot;
import com.vehicle.models.Vehicle;

@RestController
@RequestMapping("/toll")
public class ParkingController {

	@Autowired
	private ParkingService parkingService;

	@RequestMapping(value = "/park/vehicle", method = RequestMethod.POST)
	@ResponseBody
	public String parkVehicle(@RequestBody Vehicle vehicle) {
		return parkingService.parkVehicle(vehicle);
	}

	@RequestMapping(value = "/unpark/vehicle/{licence}", method = RequestMethod.GET)
	@ResponseBody
	public Ticket unparkVehicle(@PathVariable String licence) {
		return parkingService.unparkVehicle(licence);
	}

	@RequestMapping(value = "/park/status", method = RequestMethod.GET)
	@ResponseBody
	public List<ParkingSlot> parkingStatus() {
		return parkingService.parkingStatus();
	}
	
}
