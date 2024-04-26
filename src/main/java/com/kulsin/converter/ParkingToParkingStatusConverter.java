package com.kulsin.converter;

import com.kulsin.dto.Parking;
import com.kulsin.models.VehicleType;
import com.kulsin.models.response.ParkingStatus;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ParkingToParkingStatusConverter implements Converter<Parking, ParkingStatus> {

    @Override
    public ParkingStatus convert(Parking source) {
        return ParkingStatus.builder()
                .slotNumber(source.getSlotNumber())
                .vehicleType(VehicleType.customValueOf(source.getVehicleType()))
                .plateNumber(source.getVehicleRegistration())
                .entryTime(source.getEntryTime().toString())
                .color(source.getColor())
                .brand(source.getBrand())
                .build();
    }

}
