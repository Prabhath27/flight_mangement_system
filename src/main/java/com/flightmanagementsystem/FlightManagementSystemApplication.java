package com.flightmanagementsystem;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FlightManagementSystemApplication {
	@Bean
	  public ModelMapper modelMapper() {
	    return new ModelMapper();
	  }

	public static void main(String[] args) {
		SpringApplication.run(FlightManagementSystemApplication.class, args);
	}

}
