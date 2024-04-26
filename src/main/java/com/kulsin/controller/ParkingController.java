package com.kulsin.controller;

import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.service.ParkingOperationsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class ParkingController {

    private ParkingOperationsService parkingOperationsService;

    @PostMapping(value = "/park")
    public ResponseEntity<ParkUnparkResponse> park(@RequestBody ParkRequest parkRequest) {
        return ResponseEntity.ok(parkingOperationsService.parkVehicle(parkRequest));
    }

    @PostMapping(value = "/unpark")
    public ResponseEntity<ParkUnparkResponse> unpark(@RequestBody UnparkRequest unparkRequest) {
        return ResponseEntity.ok(parkingOperationsService.unparkVehicle(unparkRequest));
    }

    @PostMapping(value = "/generate-bill")
    public ResponseEntity<GenerateBillResponse> generateBill(@RequestParam("plate-number") String plateNumber) {
        return ResponseEntity.ok(parkingOperationsService.generateBill(plateNumber));
    }

    @GetMapping(value = "/parking-status")
    public ResponseEntity<List<ParkingStatus>> parkingStatus() {
        return ResponseEntity.ok(parkingOperationsService.currentParkingLotStatus());
    }

    @GetMapping(value = "/search-vehicle")
    public ResponseEntity<ParkingStatus> searchVehicle(@RequestParam("plate-number") String plateNumber) {
        return ResponseEntity.ok(parkingOperationsService.searchVehicleByRegistration(plateNumber));
    }

    @GetMapping(value = "/parking-slot/{slot_number}")
    public ResponseEntity<ParkingStatus> searchByParkingSlot(@PathVariable("slot_number") String slotNumber) {
        return ResponseEntity.ok(parkingOperationsService.getParkingSlotDetails(slotNumber));
    }

}