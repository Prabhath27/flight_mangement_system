package com.flightmanagementsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.FlightDao;
import com.flightmanagementsystem.dto.FlightDTO;
import com.flightmanagementsystem.entity.Flight;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.FlightManagementException;
import com.flightmanagementsystem.repository.FlightRepository;

//Service annotation indicates that this class is a service and should be picked up by Spring component scanning.
@Service
public class FlightService implements FlightDao
{

  // Autowired annotation injects the FlightRepository bean into this field.
  @Autowired
	private FlightRepository flightRepository;
  // Autowired annotation injects the ModelMapper bean into this field.
  @Autowired
	private ModelMapper modelMapper;


  // Method to add a flight by converting DTO to Entity and then saving it.
  @Override
	public FlightDTO addFlight(FlightDTO flightDto) {
		//converting DTO to Entity
	Flight flightRequest = modelMapper.map(flightDto, Flight.class);
	Flight flight = flightRepository.save(flightRequest);
		//converting Entity to DTO
	FlightDTO flightResponse = modelMapper.map(flight, FlightDTO.class); 
	return flightResponse;
	}

  // Method to view a flight by flightId.
  @Override
	public FlightDTO viewByFlightId(Integer flightId){
      // Check if the flight with the given id exists.
		if (flightRepository.existsById(flightId) == false) {
			throw new FlightManagementException("Flight does not exist");
		} else
		{
          // Retrieve the flight by id and convert it to DTO.

		    FlightDTO flightResponse=modelMapper.map(flightRepository.findById(flightId).get(),FlightDTO.class);

		
		return flightResponse;
		}
	}

  // Method to view all flights.
  @Override
	public List<FlightDTO> viewAllFlights(){
      // Retrieve all flights from the repository and convert them to DTOs using stream and collect.
		List<FlightDTO> flightResponse= flightRepository.findAll().stream()
				.map((Flight flight)->modelMapper.map(flight, FlightDTO.class))
				.collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no flights are available.
      if (flightResponse.size() == 0) 
      {
			throw new FlightManagementException("No flights ");
		} 
      else
			return flightResponse;
	}

  // Method to view flights sorted by flight name.
  @Override
	public List<FlightDTO> viewByFlightName()
  {
      // Retrieve all flights from the repository, sort them by flight name, and convert them to DTOs.
		 List<Flight> flight= flightRepository.findAll().stream().sorted((a, b) -> a.getFlightName().compareTo(b.getFlightName()))
				.collect(Collectors.toList());
		 List<FlightDTO> flightResponse=flight.stream()
					.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
					.collect(Collectors.toList());
	        // Check if the list is empty and throw an exception if no flights are available.
          if (flightResponse.size() == 0) 
          {
				throw new FlightManagementException("No flights .");
			} 
          else
				return flightResponse;
	}
  // Method to update a flight by converting DTO to Entity and saving it.
  @Override
	public FlightDTO updateFlight(FlightDTO flightDto){
      // Initialize flightDTO to null.
      FlightDTO flight = null;
		//converting DTO to EntitymodelMapper.
		Flight flightRequest = modelMapper.map(flightDto, Flight.class);

      // Check if the flight with the given id exists.
      if(flightRepository.existsById(flightRequest.getFlightId())) {
			FlightDTO flightResponse=modelMapper
					.map(flightRepository.save(flightRequest), FlightDTO.class);
			return flightResponse;
		}
		else 
		{
          // Throw an exception if no flights are updated.

		 throw new FlightManagementException("No flights updated ");
		}
			}
  // Method to view flights sorted by seat capacity.
  @Override
	public List<FlightDTO> viewByFlightSeatCapacity()
  {
      // Retrieve all flights from the repository, sort them by seat capacity, and convert them to DTOs.
		List<Flight> flight=flightRepository.findAll().stream()
				.sorted((a, b) -> Integer.compare(a.getSeatCapacity(), b.getSeatCapacity()))
				.collect(Collectors.toList());
		List<FlightDTO> flightResponse=flight.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no flights are available.
      if (flightResponse.size() == 0) 
		{
			throw new FlightManagementException("No flights .");
		} 
		else
			return flightResponse;
	}

  // Method to view flights by source, destination, and departure date.
  @Override
	public List<FlightDTO> viewBySourceDestination(String source, String destination)
  {
      // Initialize a list to store flights with matching source, destination, and departure date.
		ArrayList<Flight> a=new ArrayList<>(); 
      // Retrieve all flights from the repository.
      List<Flight> bk1=flightRepository.findAll();
      // Iterate through flights and schedules to find matching flights.
    for(Flight flight:bk1) 
    {
		for (Schedule schedule : flight.getSchedules()) 
		{
			if (schedule.getSourceAirport().getAirportCity().equals(source)
&& schedule.getDestinationAirport().getAirportCity().equals(destination))
			{
				a.add(flight);
			}
		}
	  }
    // Convert matching flights to DTOs using stream and collect.
    List<FlightDTO> flightResponse=a.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
    // Check if the list is empty and throw an exception if no flights are available for the given source and destination.
    if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights for given source and destination.");
		} else
			return flightResponse;
	}

  // Method to view flights by departure date.
  @Override
	public List<FlightDTO> viewByDepartureDate(LocalDate date)
  {
      // Initialize a list to store flights with matching departure date.
		ArrayList<Flight> a=new ArrayList<>(); 
      // Retrieve all flights from the repository.
      List<Flight> bk1=flightRepository.findAll();
      // Iterate through flights and schedules to find matching flights.
    for(Flight flight:bk1) 
    {
		for (Schedule schedule : flight.getSchedules()) 
		{
			if (schedule.getDepartureDate().isEqual(date)) 
			{
				a.add(flight);
			}
		}
	  }
    // Convert matching flights to DTOs using stream and collect.
    List<FlightDTO> flightResponse=a.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
    // Check if the list is empty and throw an exception if no flights are available for the given date.
    if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights for given date.");
		} else
			return flightResponse;
	}

  // Method to view flights by source, destination, and departure date.
  @Override
	public List<FlightDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,LocalDate departureDate)
  {
      // Retrieve flights from the repository that match the source, destination, and departure date.
		List<Flight> flightResponse=flightRepository.findAll().stream()
				.filter(e -> hasMatchingSourceDestinationAndDepartureDate(e, source, destination, departureDate))
				.collect(Collectors.toList());
      // Convert matching flights to DTOs using stream and collect.
      List<FlightDTO> flightResponseDto=flightResponse.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no flights are available for the given source, destination, and date.
      if (flightResponseDto.size() == 0) 
		{
			throw new FlightManagementException("No flights for given source and destination and date.");
		} 
		else
			return flightResponseDto;
	}
	//method to check for matching source,destination and departure date of a flight
	private boolean hasMatchingSourceDestinationAndDepartureDate(Flight flight, String source, String destination,
			LocalDate departureDate) {
		for (Schedule schedule : flight.getSchedules()) {
			if (schedule.getSourceAirport().getAirportCity().equals(source)
&& schedule.getDestinationAirport().getAirportCity().equals(destination)
&& schedule.getDepartureDate().isEqual(departureDate)) {
				return true;
			}
		}
		return false;


		
	}

}