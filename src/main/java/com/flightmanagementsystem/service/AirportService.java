package com.flightmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.AirportDao;
import com.flightmanagementsystem.dto.AirportDTO;
import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.exception.AirportManagementException;
import com.flightmanagementsystem.repository.AirportRepository;



@Service
public class AirportService implements AirportDao
{
    // Autowired annotation injects the AirportRepository bean into this field.
 
    @Autowired
	private AirportRepository airportRepository;
    // Autowired annotation injects the ModelMapper bean into this field.
 
    @Autowired
    private ModelMapper modelMapper;
    // Method to add airport details by converting DTO to Entity and then saving it.
 
	@Override
	public AirportDTO addAirportDetails(AirportDTO airportDto) {
		// convert DTO to Entity
		Airport airportRequest=modelMapper.map(airportDto, Airport.class);
		Airport airport=airportRepository.save(airportRequest);
		// convert Entity to DTO 
				AirportDTO airportResponse=modelMapper.map(airport,AirportDTO.class);
		return airportResponse;
	}
    // Method to view all airports by converting entities to DTOs.
 
	@Override
	public List<AirportDTO> viewAirports() {

        // Retrieve all airports from the repository and convert them to DTOs using stream and collect.
 
		List<AirportDTO> airportList=airportRepository.findAll().stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
        // Check if the list is empty and throw an exception if no airports are available.
 
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available.");
		} else
			return airportList;
	}

    // Method to view airports by country by converting entities to DTOs.
 
	@Override
	public List<AirportDTO> viewByCountry(String country){
        // Retrieve airports by country from the repository and convert them to DTOs using stream and collect.
		List<AirportDTO> airportList=airportRepository.findByAirportCountry(country).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
        // Check if the list is empty and throw an exception if no airports are available in the given country.
 
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available in given country.");
		} else
			return airportList;
	}
	// Method to view airports by city by converting entities to DTOs.
 
	@Override
	public List<AirportDTO> viewByCity(String city)
	{
	    // Retrieve airports by city from the repository and convert them to DTOs using stream and collect.
		List<AirportDTO> airportList=airportRepository.findByAirportCity(city).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
	    // Check if the list is empty and throw an exception if no airports are available in the given city.
 
		if (airportList.size() == 0) 
		{
			throw new AirportManagementException("No airport available in given city.");
		} 
		else
			return airportList;
	}
	// Method to view airports by airport name by converting entities to DTOs.
 
	@Override
	public List<AirportDTO> viewByAirportName(String airportName) 
	{
	    // Retrieve airports by airport name from the repository and convert them to DTOs using stream and collect.
		List<AirportDTO> airportList= airportRepository.findByAirportName(airportName).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
	    // Check if the list is empty and throw an exception if no airports are available for the given airport name.
 
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available for given airport name.");
		} else
			return airportList;
	}
	// Method to update airport details by converting DTO to Entity and saving it.
 
	@Override
	public AirportDTO updateAirportDetails(AirportDTO airportDto) {
		// convert DTO to Entity
				Airport airportRequest=modelMapper.map(airportDto, Airport.class);
			    // Check if the airport with the given id exists
 
				if(airportRepository.existsById(airportRequest.getAirportId())) 
				{
			        // Save the updated airport Entity
 
				   Airport airport=airportRepository.save(airportRequest);
				// convert Entity to DTO 
						AirportDTO airportResponse=modelMapper.map(airport,AirportDTO.class);
	return airportResponse;
				}
				else 
				{
			        // Throw an exception if no record is present for the given id to update.
 
					throw new AirportManagementException("No record present to update for given id to update.");
 
				}
}
	}