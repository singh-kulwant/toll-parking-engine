package com.kulsin.service;

import com.kulsin.config.ParkingLotConfiguration;
import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.repository.ParkedVehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@AllArgsConstructor
public class ParkingAdministrationServiceImpl implements ParkingAdministrationService {

    private ParkingLotConfiguration configuration;
    private ParkedVehicleRepository repository;

    @Override
    public Long calculateBill(Parking parking) {
        VehicleType vehicleType = VehicleType.customValueOf(parking.getVehicleType());

        Instant entryTime = parking.getEntryTime().toInstant();
        Instant exitTime = Instant.now();

        LocalDateTime entryDateTime = LocalDateTime.ofInstant(entryTime, ZoneOffset.UTC);
        LocalDateTime exitDateTime = LocalDateTime.ofInstant(exitTime, ZoneOffset.UTC);

        long hoursDifference = java.time.Duration.between(entryDateTime, exitDateTime).toHours();

        Long fixedCharge = configuration.getFixedCharge(vehicleType);
        Long hourlyBillingRate = configuration.getHourlyBillingRate(vehicleType);

        return fixedCharge + (hoursDifference * hourlyBillingRate);
    }

    @Override
    public String getSlotNumber(VehicleType vehicleType) {
        int capacity = configuration.getVehicleTypeCapacity(vehicleType);
        int vehicleParked = repository.countByVehicleType(vehicleType.getName());

        if (vehicleParked + 1 > capacity)
            throw new RuntimeException("Parking full");

        return String.format("%s_slot_%d", vehicleType, vehicleParked + 1);
    }

}
