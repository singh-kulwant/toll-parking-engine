package com.kulsin.rest;

import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.service.ParkingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/api/parking-lot")
public class ParkingController {

	private ParkingService parkingService;

	@PostMapping(value = "/park")
	public ResponseEntity<ParkUnparkResponse> park(ParkRequest parkRequest) {
		return ResponseEntity.ok(parkingService.parkVehicle(parkRequest));
	}

	@PostMapping(value = "/unpark")
	public ResponseEntity<ParkUnparkResponse> unpark(UnparkRequest unparkRequest) {
		return ResponseEntity.ok(parkingService.unparkVehicle(unparkRequest));
	}

	@PostMapping(value = "/generate-bill")
	public ResponseEntity<GenerateBillResponse> generateBill(@RequestParam("plate-number") String plateNumber) {
		return ResponseEntity.ok(parkingService.generateBill(plateNumber));
	}

	@GetMapping(value = "/parking-status")
	public ResponseEntity<List<ParkingStatus>> parkingStatus() {
		return ResponseEntity.ok(parkingService.currentParkingLotStatus());
	}

	@GetMapping(value = "/search-vehicle")
	public ResponseEntity<ParkingStatus> searchVehicle(@RequestParam("plate-number") String plateNumber) {
		return ResponseEntity.ok(parkingService.searchVehicleByRegistration(plateNumber));
	}

}