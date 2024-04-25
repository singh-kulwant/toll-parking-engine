package com.kulsin.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkedVehicleRepository extends JpaRepository<Parking, Long> {

    void deleteByVehicleRegistration(String vehicleRegistration);

    Parking findParkingByVehicleRegistration(String vehicleRegistration);

    Parking findBySlotNumber(String slotNumber);

}
