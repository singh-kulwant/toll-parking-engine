package com.kulsin.service;

import com.kulsin.converter.ParkRequestToParkingConverter;
import com.kulsin.converter.ParkingToParkingStatusConverter;
import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;
import com.kulsin.repository.ParkedVehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.GenericConversionService;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ParkingOperationsServiceTest {

    private ParkedVehicleRepository repository;
    private ParkingAdministrationService administrationService;
    private ParkingOperationsService parkingOperationsService;

    @BeforeEach
    public void setUp() {

        GenericConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(new ParkingToParkingStatusConverter());
        conversionService.addConverter(new ParkRequestToParkingConverter());

        repository = mock(ParkedVehicleRepository.class);
        administrationService = mock(ParkingAdministrationService.class);
        parkingOperationsService = new ParkingOperationsServiceImpl(conversionService, repository, administrationService);
    }

    @Test
    public void testParkVehicle() {
        // Mock park request
        ParkRequest parkRequest = new ParkRequest();
        parkRequest.setPlateNumber("ABC123");
        parkRequest.setVehicleType("CAR");

        // Mock administration service
        when(administrationService.getSlotNumber(any())).thenReturn("A1");

        // Perform park operation
        ParkUnparkResponse response = parkingOperationsService.parkVehicle(parkRequest);

        // Verify the result
        assertTrue(response.isSuccess());
        assertEquals("Parked successfully", response.getMessage());
    }

    @Test
    public void testUnparkVehicle() {
        // Mock unpark request
        UnparkRequest unparkRequest = new UnparkRequest();
        unparkRequest.setPlateNumber("ABC123");

        when(repository.deleteByVehicleRegistration("ABC123")).thenReturn(1L);

        // Perform unpark operation
        ParkUnparkResponse response = parkingOperationsService.unparkVehicle(unparkRequest);

        // Verify the result
        assertTrue(response.isSuccess());
        assertEquals("Unparked successfully", response.getMessage());

        // Verify if deleteByVehicleRegistration method is called with correct parameter
        verify(repository).deleteByVehicleRegistration("ABC123");
    }

    @Test
    public void testGenerateBill() {
        // Mock parking object
        Parking parking = mockParking("ABC123");

        // Mock repository method
        when(repository.findParkingByVehicleRegistration("ABC123")).thenReturn(parking);

        // Mock administration service
        when(administrationService.calculateBill(parking)).thenReturn(100L);

        // Perform generateBill operation
        GenerateBillResponse response = parkingOperationsService.generateBill("ABC123");

        // Verify the result
        assertNotNull(response);
        assertEquals(100L, response.getBillAmount());
    }

    @Test
    public void testCurrentParkingLotStatus() {
        // Mock parking objects
        Parking parking1 = mockParking("ABC123");
        Parking parking2 = mockParking("DEF456");

        // Mock repository method
        when(repository.findAll()).thenReturn(List.of(parking1, parking2));

        // Perform currentParkingLotStatus operation
        List<ParkingStatus> statusList = parkingOperationsService.currentParkingLotStatus();

        // Verify the result
        assertNotNull(statusList);
        assertEquals(2, statusList.size());
    }

    @Test
    public void testSearchVehicleByRegistration() {
        // Mock parking object
        Parking parking = mockParking("ABC123");

        // Mock repository method
        when(repository.findParkingByVehicleRegistration("ABC123")).thenReturn(parking);

        // Perform searchVehicleByRegistration operation
        ParkingStatus status = parkingOperationsService.searchVehicleByRegistration("ABC123");

        // Verify the result
        assertNotNull(status);
        assertEquals("ABC123", status.getPlateNumber());
    }

    @Test
    public void testGetParkingSlotDetails() {
        // Mock parking object
        Parking parking = mockParking("ABC123");

        // Mock repository method
        when(repository.findBySlotNumber("GASOLINE_slot_1")).thenReturn(parking);

        // Perform getParkingSlotDetails operation
        ParkingStatus status = parkingOperationsService.getParkingSlotDetails("GASOLINE_slot_1");

        // Verify the result
        assertNotNull(status);
        assertEquals("GASOLINE_slot_1", status.getSlotNumber());
    }

    private static Parking mockParking(String vehicleRegistration) {
        Parking parking = new Parking();
        parking.setVehicleType(VehicleType.GASOLINE.getName());
        parking.setBrand("Toyota");
        parking.setColor("Red");
        parking.setSlotNumber("GASOLINE_slot_1");
        parking.setEntryTime(Date.from(Instant.now()));
        parking.setVehicleRegistration(vehicleRegistration);
        return parking;
    }

}