package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "com.controller", "com.slot.models" })
@EnableConfigurationProperties(ConstantProperties.class)
public class TollParkingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TollParkingApplication.class, args);
	}

}
