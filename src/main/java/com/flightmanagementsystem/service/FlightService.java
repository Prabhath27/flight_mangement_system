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

@Service
public class FlightService implements FlightDao {
	
	@Autowired
	private FlightRepository flightRepository;
	
	@Autowired
	private ModelMapper modelMapper;


	@Override
	public FlightDTO addFlight(FlightDTO flightDto) {
		//converting DTO to Entity
	Flight flightRequest = modelMapper.map(flightDto, Flight.class);
	Flight flight = flightRepository.save(flightRequest);
		//converting Entity to DTO
	FlightDTO flightResponse = modelMapper.map(flight, FlightDTO.class); 
	return flightResponse;
		
	}

	@Override
	public FlightDTO viewByFlightId(Integer flightId){
		// TODO Auto-generated method stub
		if (flightRepository.existsById(flightId) == false) {
			throw new FlightManagementException("Flight does not exist");
		} else
		{
		FlightDTO flightResponse=modelMapper.map(flightRepository.findById(flightId).get(),FlightDTO.class);

		
		return flightResponse;
		}
	}

	@Override
	public List<FlightDTO> viewAllFlights(){
		// TODO Auto-generated method stub
		List<FlightDTO> flightResponse= flightRepository.findAll().stream()
				.map((Flight flight)->modelMapper.map(flight, FlightDTO.class))
				.collect(Collectors.toList());
		if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights ");
		} else
			return flightResponse;
	}

	@Override
	public List<FlightDTO> viewByFlightName(){
		// TODO Auto-generated method stub
		
		 List<Flight> flight= flightRepository.findAll().stream().sorted((a, b) -> a.getFlightName().compareTo(b.getFlightName()))
				.collect(Collectors.toList());
		 
		 List<FlightDTO> flightResponse=flight.stream()
					.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
					.collect(Collectors.toList());
		 if (flightResponse.size() == 0) {
				throw new FlightManagementException("No flights .");
			} else
				return flightResponse;
		 
		 
	}
	@Override
	public FlightDTO updateFlight(FlightDTO flightDto){
	
		FlightDTO flight = null;
		
		//converting DTO to EntitymodelMapper.
		Flight flightRequest = modelMapper.map(flightDto, Flight.class);

		if(flightRepository.existsById(flightRequest.getFlightId())) {
			
			FlightDTO flightResponse=modelMapper
					.map(flightRepository.save(flightRequest), FlightDTO.class);
			return flightResponse;
		}
		else {
		 throw new FlightManagementException("No flights updated ");
		}
		
			}
	@Override
	public List<FlightDTO> viewByFlightSeatCapacity(){
		// TODO Auto-generated method stub
		List<Flight> flight=flightRepository.findAll().stream()
				.sorted((a, b) -> Integer.compare(a.getSeatCapacity(), b.getSeatCapacity()))
				.collect(Collectors.toList());
		List<FlightDTO> flightResponse=flight.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
		if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights .");
		} else
			return flightResponse;
	}

	@Override
	public List<FlightDTO> viewBySourceDestination(String source, String destination){
		// TODO Auto-generated method stub
		ArrayList<Flight> a=new ArrayList<>(); 
		
        List<Flight> bk1=flightRepository.findAll();
		
		for(Flight flight:bk1) {
		for (Schedule schedule : flight.getSchedules()) {
			if (schedule.getSourceAirport().getAirportCity().equals(source)
					&& schedule.getDestinationAirport().getAirportCity().equals(destination))
			{
				a.add(flight);
			}}}
		List<FlightDTO> flightResponse=a.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
		if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights for given source and destination.");
		} else
			return flightResponse;
	}

	@Override
	public List<FlightDTO> viewByDepartureDate(LocalDate date){
		// TODO Auto-generated method stub
		ArrayList<Flight> a=new ArrayList<>(); 
		
		List<Flight> bk1=flightRepository.findAll();
		for(Flight flight:bk1) {
		for (Schedule schedule : flight.getSchedules()) {
			if (schedule.getDepartureDate().isEqual(date)) {
				a.add(flight);
			}}}
		List<FlightDTO> flightResponse=a.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
		if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights for given date.");
		} else
			return flightResponse;
	}

	@Override
	public List<FlightDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,LocalDate departureDate) {
		// TODO Auto-generated method stub
ArrayList<Flight> a=new ArrayList<>(); 
		
		List<Flight> bk1=flightRepository.findAll();
		for(Flight flight:bk1) {
			for (Schedule schedule : flight.getSchedules()) {
				if (schedule.getSourceAirport().getAirportCity().equals(source)
						&& schedule.getDestinationAirport().getAirportCity().equals(destination)
						&& schedule.getDepartureDate().isEqual(departureDate)) 
				{
					a.add(flight);
				}
				
		}}
		
		List<FlightDTO> flightResponse=a.stream()
				.map((Flight flight1)->modelMapper.map(flight1, FlightDTO.class))
				.collect(Collectors.toList());
		if (flightResponse.size() == 0) {
			throw new FlightManagementException("No flights for given source and destination and date.");
		} else
			return flightResponse;

		
	}

	
	}

