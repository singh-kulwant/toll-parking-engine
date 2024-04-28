package com.kulsin.repository;

import com.kulsin.dto.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkedVehicleRepository extends JpaRepository<Parking, Long> {

    long deleteByVehicleRegistration(String vehicleRegistration);

    Parking findParkingByVehicleRegistration(String vehicleRegistration);

    Parking findBySlotNumber(String slotNumber);

    int countByVehicleType(String vehicleType);

}
