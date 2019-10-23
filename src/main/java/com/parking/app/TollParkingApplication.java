package com.parking.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

import com.parking.config.ParkingConfigurationLoader;

@SpringBootApplication
@ComponentScan({"com.parking.rest","com.parking.config","com.parking.service"})
public class TollParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollParkingApplication.class, args);
	}

}
