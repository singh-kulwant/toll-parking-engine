package com.kulsin.repository;

import com.kulsin.dto.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ParkedVehicleRepository extends JpaRepository<Parking, Long> {

    void deleteByVehicleRegistration(String vehicleRegistration);

    Optional<Parking> findParkingByVehicleRegistration(String vehicleRegistration);

    Optional<Parking> findBySlotNumber(String slotNumber);

    int countByVehicleType(String vehicleType);

}
