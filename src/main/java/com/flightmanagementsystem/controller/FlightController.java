package com.flightmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.FlightDTO;
import com.flightmanagementsystem.service.FlightService;

import jakarta.validation.Valid;

@RestController
public class FlightController {

	@Autowired
	public FlightService flightService;

	@PostMapping("/addFlight")
	public String addFlight(@Valid  @RequestBody FlightDTO flightDto) {
		FlightDTO flightResponse=flightService.addFlight(flightDto);
		return "flight added";
	}
	@GetMapping("/viewByFlightId/{flightId}")
	public FlightDTO viewById(@PathVariable("flightId") Integer flightId ) {
		return flightService.viewByFlightId(flightId);
	}
	
	@GetMapping("/viewAllFlights")
	public List<FlightDTO> viewFlights(){
		return flightService.viewAllFlights();
	}
	
	@GetMapping("/viewByFlightName")
	public List<FlightDTO> viewFlightName(){
		
		return flightService.viewByFlightName();
	}
	@PutMapping("/updateFlights")
	public String updateDetails(@Valid @RequestBody FlightDTO flight) {
		FlightDTO flightDto=flightService.updateFlight(flight);
		if(flightDto==null) {
		return "record not found";
		}
		else {
			return "record updated";
		}
		
		}
	
	@GetMapping("/viewByFlightSeatCapacity")
	public List<FlightDTO> viewByFlightSeatCapacity(){
		
		return flightService.viewByFlightSeatCapacity();


	}
	@GetMapping("/viewBySourceDestinationFlight/{source}/{destination}")
	public List<FlightDTO> viewBySourceDestination(@PathVariable("source") String source,@PathVariable("destination") String destination){
		
		return flightService.viewBySourceDestination(source, destination);
	}
	@GetMapping("/viewByDepartureDateFlight/{date}")
	public List<FlightDTO> viewBydateTime(@PathVariable("date") LocalDate date){
		return flightService.viewByDepartureDate(date);
	}
	
@GetMapping("/viewBySourceDestinationAndDepartureDateFlight/{source}/{destination}/{departureDate}")
public List<FlightDTO> getBySourceDestinationAndDepartureDate(@PathVariable("source")String source,@PathVariable("destination")String destination,
			@PathVariable("departureDate")LocalDate departureDate) 
	{
		return flightService.viewBySourceDestinationAndDepartureDate(source,destination,departureDate);
	}

}
