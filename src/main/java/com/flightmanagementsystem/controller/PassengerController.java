package com.flightmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.PassengerDTO;
import com.flightmanagementsystem.service.PassengerService;

import jakarta.validation.Valid;

@RestController
public class PassengerController {

	@Autowired
	public PassengerService passengerService;
	

	@PostMapping("/addPassengers")
	public String addPassenger(@Valid @RequestBody PassengerDTO passengerDto) {
		
		passengerService.addPassenger(passengerDto);
		
		return"record added";
	}
	
	@GetMapping("/viewAllPassenger")
	public List<PassengerDTO> viewAllPassenger() {
		
		return passengerService.viewAllPassenger();
	}
	
	@GetMapping("viewPassengerByUIN/{uin}")
	public PassengerDTO viewPassengerByUIN(@PathVariable("uin") Long uin) {
		return passengerService.viewPassengerByUIN(uin);
}

	@GetMapping("viewPassengerByMobileNo/{contactNo}")
	public PassengerDTO viewPassengerByMobileNo(@PathVariable("contactNo") String contactNo) {
		
		return passengerService.viewPassengerByMobileNo(contactNo);
}
}
