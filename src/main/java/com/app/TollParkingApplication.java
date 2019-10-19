package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.parking.api.ParkingConfigurationLoader;

@SpringBootApplication
@EnableConfigurationProperties(ParkingConfigurationLoader.class)
@ComponentScan("com.parking.api")
public class TollParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollParkingApplication.class, args);
	}

}
