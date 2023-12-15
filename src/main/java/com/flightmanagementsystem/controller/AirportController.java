package com.flightmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.AirportDTO;
import com.flightmanagementsystem.service.AirportService;

import jakarta.validation.Valid;

@RestController
public class AirportController {
	@Autowired
	private AirportService airportService;
	
	@PostMapping("/addAirportDetails")
	public String addAirportDetails(@Valid @RequestBody AirportDTO airportDto) {
		
		
		AirportDTO airportResponse=airportService.addAirportDetails(airportDto);
		
		 return "data added";
}
	@GetMapping("/viewAirports")
   public List<AirportDTO> viewAirports(){
		
		return airportService.viewAirports();
		
	}
	@GetMapping("/viewByCountry/{country}")
	public List<AirportDTO> viewByCountry(@PathVariable("country") String country) {
		
		return airportService.viewByCountry(country);
}
	@GetMapping("/viewByCity/{city}")
	public List<AirportDTO> viewByCity(@PathVariable("city") String city) {
		
		return airportService.viewByCity(city);
}
	@GetMapping("/viewByAirportName/{airportName}")
	public List<AirportDTO> viewByAirportName(@PathVariable("airportName") String name) {
		
		return airportService.viewByAirportName(name);
}
	@PutMapping("/updateAirportDetails")
	public String updateAirportDetails(@Valid @RequestBody AirportDTO airportDto) {
		
		AirportDTO airportReponse=airportService.updateAirportDetails(airportDto);
		
		return "Record is updated ";
		
	}
}
