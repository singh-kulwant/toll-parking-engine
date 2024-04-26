package com.kulsin.config;

import com.kulsin.models.VehicleType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties("com.kulsin.parking-lot")
public class ParkingLotConfiguration {

    private ParkingLot standard;
    private ParkingLot heavyElectric;
    private ParkingLot lightElectric;

    public Long getHourlyBillingRate(VehicleType vehicleType) {
        return switch (vehicleType) {
            case LIGHT_ELECTRIC -> this.lightElectric.getHourlyBillingRate();
            case HEAVY_ELECTRIC -> this.heavyElectric.getHourlyBillingRate();
            default -> this.standard.getHourlyBillingRate();
        };
    }

    public Long getFixedCharge(VehicleType vehicleType) {
        return switch (vehicleType) {
            case LIGHT_ELECTRIC -> this.lightElectric.getFixedCharge();
            case HEAVY_ELECTRIC -> this.heavyElectric.getFixedCharge();
            default -> this.standard.getFixedCharge();
        };
    }

    public int getVehicleTypeCapacity(VehicleType vehicleType) {
        return switch (vehicleType) {
            case LIGHT_ELECTRIC -> this.lightElectric.getCapacity();
            case HEAVY_ELECTRIC -> this.heavyElectric.getCapacity();
            default -> this.standard.getCapacity();
        };
    }

    @Data
    public static class ParkingLot {
        public int capacity;
        public Long fixedCharge;
        public Long hourlyBillingRate;
    }

}
