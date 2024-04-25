package com.kulsin.service;

import com.kulsin.entity.ParkedVehicleRepository;
import com.kulsin.entity.Parking;
import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ParkingServiceImpl implements ParkingService {

    private ParkedVehicleRepository repository;

    @Override
    public ParkUnparkResponse parkVehicle(ParkRequest parkRequest) {
        Parking parking = Parking.builder()
                .brand(parkRequest.getBrand())
                .color(parkRequest.getColor())
                .entryTime(Date.from(Instant.now()))
                .slotNumber(parkRequest.getSlotNumber())
                .vehicleRegistration(parkRequest.getPlateNumber())
                .vehicleType(parkRequest.getVehicleType())
                .build();

        repository.save(parking);

        return ParkUnparkResponse.builder().success(true).message(null).build();
    }

    @Override
    public ParkUnparkResponse unparkVehicle(UnparkRequest unparkRequest) {
        repository.deleteByVehicleRegistration(unparkRequest.getPlateNumber());
        return ParkUnparkResponse.builder().success(true).message(null).build();
    }

    @Override
    public GenerateBillResponse generateBill(String vehicleRegistration) {
        Parking parking = repository.findParkingByVehicleRegistration(vehicleRegistration);

        return GenerateBillResponse.builder()
                .billAmount(100L)
                .build();
    }

    @Override
    public List<ParkingStatus> currentParkingLotStatus() {
        return repository.findAll().stream()
                .map(parking -> ParkingStatus.builder()
                        .slotNumber(Integer.valueOf(parking.getSlotNumber()))
                        .vehicleType(parking.getVehicleType())
                        .plateNumber(parking.getVehicleRegistration())
                        .entryTime(parking.getEntryTime().toString())
                        .color(parking.getColor())
                        .brand(parking.getBrand())
                        .build())
                .toList();
    }

    @Override
    public ParkingStatus searchVehicleByRegistration(String vehicleRegistration) {
        Parking parking = repository.findParkingByVehicleRegistration(vehicleRegistration);

        return ParkingStatus.builder()
                .slotNumber(Integer.valueOf(parking.getSlotNumber()))
                .vehicleType(parking.getVehicleType())
                .plateNumber(parking.getVehicleRegistration())
                .entryTime(parking.getEntryTime().toString())
                .color(parking.getColor())
                .brand(parking.getBrand())
                .build();
    }

    @Override
    public ParkingStatus getParkingSlotDetails(int slotNumber) {
        Parking parking = repository.findBySlotNumber(String.valueOf(slotNumber));

        return ParkingStatus.builder()
                .slotNumber(Integer.valueOf(parking.getSlotNumber()))
                .vehicleType(parking.getVehicleType())
                .plateNumber(parking.getVehicleRegistration())
                .entryTime(parking.getEntryTime().toString())
                .color(parking.getColor())
                .brand(parking.getBrand())
                .build();
    }

}
