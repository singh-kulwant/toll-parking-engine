package com.kulsin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
@ConfigurationProperties("com.kulsin.parking-lot")
public class ParkingLotConfiguration {

    public ParkingLot standard;
    public ParkingLot heavyElectric;
    public ParkingLot lightElectric;

    @Data
    public static class ParkingLot {
        public int capacity;
        public Long fixedCharge;
        public Long hourlyBillingRate;
    }

}
