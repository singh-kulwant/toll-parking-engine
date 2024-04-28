package com.kulsin.service;

import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.repository.ParkedVehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        long numOfEntriesDeleted = repository.deleteByVehicleRegistration(unparkRequest.getPlateNumber());
        if (numOfEntriesDeleted == 0) {
            throw new EntityNotFoundException(String.format("Vehicle %s not found!", unparkRequest.getPlateNumber()));
        }
        return ParkUnparkResponse.builder().success(true).message("Unparked successfully").build();
    }

    @Override
    public GenerateBillResponse generateBill(String vehicleRegistration) {
        Parking parking = repository.findParkingByVehicleRegistration(vehicleRegistration);

        if (parking == null) {
            throw new EntityNotFoundException(String.format("Vehicle %s not found!", vehicleRegistration));
        }

        return GenerateBillResponse.builder()
                .billAmount(administrationService.calculateBill(parking))
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
        Parking parking = repository.findParkingByVehicleRegistration(vehicleRegistration);
        if (parking == null) {
            throw new EntityNotFoundException(String.format("Vehicle %s not found!", vehicleRegistration));
        }
        return conversionService.convert(parking, ParkingStatus.class);
    }

    @Override
    public ParkingStatus getParkingSlotDetails(String slotNumber) {
        Parking parking = repository.findBySlotNumber(slotNumber);
        if (parking == null) {
            throw new EntityNotFoundException(String.format("Parking slot %s not found!", slotNumber));
        }
        return conversionService.convert(parking, ParkingStatus.class);
    }

}
