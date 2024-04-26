package com.kulsin.service;

import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;

public interface ParkingAdministrationService {

    /**
     * Calculates the bill for a parked vehicle based on the parking duration.
     *
     * @param parking The parking information containing details of the parked vehicle.
     * @return The bill amount for the parked vehicle.
     */
    Long calculateBill(Parking parking);

    /**
     * Allocates a parking slot for a vehicle of the specified type.
     *
     * @param vehicleType The type of vehicle for which a slot is to be allocated.
     * @return The slot number allocated for the vehicle.
     */
    String getSlotNumber(VehicleType vehicleType);

}
