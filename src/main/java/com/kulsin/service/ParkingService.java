package com.kulsin.service;

import com.kulsin.models.request.ParkRequest;
import com.kulsin.models.request.UnparkRequest;
import com.kulsin.models.response.GenerateBillResponse;
import com.kulsin.models.response.ParkUnparkResponse;
import com.kulsin.models.response.ParkingStatus;

import java.util.List;

public interface ParkingService {
    
    /**
     * Parks a vehicle in a specified slot.
     * 
     * @param parkRequest
     * @return
     */
    ParkUnparkResponse parkVehicle(ParkRequest parkRequest);

    /**
     * Removes a vehicle from a parking slot.
     * 
     * @param unparkRequest
     * @return
     */
    ParkUnparkResponse unparkVehicle(UnparkRequest unparkRequest);

    /**
     * Generates a bill for the parked duration of a vehicle.
     * 
     * @param vehicleRegistration
     * @return
     */
    GenerateBillResponse generateBill(String vehicleRegistration);

    /**
     * Retrieves the current status of all parking slots.
     * 
     * @return
     */
    List<ParkingStatus> currentParkingLotStatus();

    /**
     * Searches for a vehicle by its plate number.
     * 
     * @param vehicleRegistration
     * @return
     */
    ParkingStatus searchVehicleByRegistration(String vehicleRegistration);

    /**
     * Retrieves details of a specific parking slot.
     * 
     * @param slotNumber
     * @return
     */
    ParkingStatus getParkingSlotDetails(int slotNumber);

}