package com.kulsin.converter;

import com.kulsin.dto.Parking;
import com.kulsin.models.request.ParkRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class ParkRequestToParkingConverter implements Converter<ParkRequest, Parking> {

    @Override
    public Parking convert(ParkRequest source) {
        return Parking.builder()
                .brand(source.getBrand())
                .color(source.getColor())
                .entryTime(Date.from(Instant.now()))
                .vehicleRegistration(source.getPlateNumber())
                .vehicleType(String.valueOf(source.getVehicleType()))
                .build();
    }

}
