package com.kulsin.service;

import com.kulsin.config.ParkingLotConfiguration;
import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.repository.ParkedVehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ParkingAdministrationServiceTest {

    private ParkingAdministrationService parkingService;
    private ParkingLotConfiguration configuration;
    private ParkedVehicleRepository repository;

    @BeforeEach
    public void setUp() {
        configuration = mock(ParkingLotConfiguration.class);
        repository = mock(ParkedVehicleRepository.class);
        parkingService = new ParkingAdministrationServiceImpl(configuration, repository);
    }

    @Test
    public void testCalculateBill() {
        // Mock Parking object
        Parking parking = new Parking();
        parking.setEntryTime(Date.from(Instant.now().minus(1, ChronoUnit.DAYS)));
        parking.setVehicleType("GASOLINE");

        // Mock configuration values
        when(configuration.getFixedCharge(any())).thenReturn(50L);
        when(configuration.getHourlyBillingRate(any())).thenReturn(10L);

        // Calculate bill
        Long bill = parkingService.calculateBill(parking);

        // Verify the result
        assertEquals(290L, bill);
    }

    @Test
    public void testGetSlotNumber() {
        // Mock configuration values
        ParkingLotConfiguration.ParkingLot standardLot = new ParkingLotConfiguration.ParkingLot();
        standardLot.setCapacity(10);
        when(configuration.getVehicleTypeCapacity(any())).thenReturn(standardLot.getCapacity());

        // Mock repository count
        when(repository.countByVehicleType(any())).thenReturn(5);

        // Get slot number
        String slotNumber = parkingService.getSlotNumber(VehicleType.GASOLINE);

        // Verify the result
        assertEquals("GASOLINE_slot_6", slotNumber);
    }

}