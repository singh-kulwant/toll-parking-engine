package com.kulsin.service;

import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.repository.ParkedVehicleRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ParkingOperationsServiceImpl implements ParkingOperationsService {

    private ConversionService conversionService;
    private ParkedVehicleRepository repository;
    private ParkingAdministrationService administrationService;

    @Override
    public ParkUnparkResponse parkVehicle(ParkRequest parkRequest) {

        Parking parking = conversionService.convert(parkRequest, Parking.class);
        parking.setSlotNumber(administrationService.getSlotNumber(VehicleType.customValueOf(parkRequest.getVehicleType())));

        repository.save(parking);

        return ParkUnparkResponse.builder().success(true).message("Parked successfully").build();

    }

    @Override
    @Transactional
    public ParkUnparkResponse unparkVehicle(UnparkRequest unparkRequest) {
        repository.deleteByVehicleRegistration(unparkRequest.getPlateNumber());
        return ParkUnparkResponse.builder().success(true).message("Unparked successfully").build();
    }

    @Override
    public GenerateBillResponse generateBill(String vehicleRegistration) {
        Optional<Parking> optionalParking = repository.findParkingByVehicleRegistration(vehicleRegistration);

        if (optionalParking.isEmpty()) {
            throw new RuntimeException("Data not found!");
        }

        return GenerateBillResponse.builder()
                .billAmount(administrationService.calculateBill(optionalParking.get()))
                .build();
    }

    @Override
    public List<ParkingStatus> currentParkingLotStatus() {
        return repository.findAll().stream()
                .map(parking -> conversionService.convert(parking, ParkingStatus.class))
                .toList();
    }

    @Override
    public ParkingStatus searchVehicleByRegistration(String vehicleRegistration) {
        Optional<Parking> optionalParking = repository.findParkingByVehicleRegistration(vehicleRegistration);

        if (optionalParking.isEmpty()) {
            throw new RuntimeException("Data not found!");
        }

        return conversionService.convert(optionalParking.get(), ParkingStatus.class);
    }

    @Override
    public ParkingStatus getParkingSlotDetails(String slotNumber) {
        Optional<Parking> optionalParking = repository.findBySlotNumber(slotNumber);

        if (optionalParking.isEmpty()) {
            throw new RuntimeException("Data not found!");
        }

        return conversionService.convert(optionalParking.get(), ParkingStatus.class);
    }

}
