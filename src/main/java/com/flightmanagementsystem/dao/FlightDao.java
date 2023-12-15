package com.flightmanagementsystem.dao;

import java.time.LocalDate;
import java.util.List;

import com.flightmanagementsystem.dto.FlightDTO;


public interface FlightDao {

	public FlightDTO addFlight(FlightDTO flightDto);

	public FlightDTO viewByFlightId(Integer flightId);

	public List<FlightDTO> viewAllFlights();

	public List<FlightDTO> viewByFlightName();

	public List<FlightDTO> viewByFlightSeatCapacity();

	public List<FlightDTO> viewBySourceDestination(String source, String destination);

	public List<FlightDTO> viewByDepartureDate(LocalDate date);

	public List<FlightDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate);

	public FlightDTO updateFlight(FlightDTO flightDto);
}
