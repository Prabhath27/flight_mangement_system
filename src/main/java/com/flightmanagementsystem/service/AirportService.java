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
public class AirportService implements AirportDao {
	
	@Autowired
	private AirportRepository airportRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public AirportDTO addAirportDetails(AirportDTO airportDto) {
		// convert DTO to Entity
		Airport airportRequest=modelMapper.map(airportDto, Airport.class);
		Airport airport=airportRepository.save(airportRequest);
		// convert Entity to DTO 
				AirportDTO airportResponse=modelMapper.map(airport,AirportDTO.class);
		return airportResponse;
	}

	@Override
	public List<AirportDTO> viewAirports() {
		
//		List<Airport> airport=airrepository.findAll() ;
//		 convert Entity to DTO 
//		List<AirportDTO> airportResponse=new ArrayList<>();
//		
//		for(Airport a:airport) {
//		
//          airportResponse.add(modelMapper.map(airport,AirportDTO.class));
//		}
//		return airportResponse;
		List<AirportDTO> airportList=airportRepository.findAll().stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available.");
		} else
			return airportList;
	}
		
	

	@Override
	public List<AirportDTO> viewByCountry(String country){
		// TODO Auto-generated method stub
		List<AirportDTO> airportList=airportRepository.findByAirportCountry(country).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available in given country.");
		} else
			return airportList;
	}

	@Override
	public List<AirportDTO> viewByCity(String city){
		// TODO Auto-generated method stub
		List<AirportDTO> airportList=airportRepository.findByAirportCity(city).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available in given city.");
		} else
			return airportList;
	}
	

	@Override
	public List<AirportDTO> viewByAirportName(String airportName) {
		// TODO Auto-generated method stub
		List<AirportDTO> airportList= airportRepository.findByAirportName(airportName).stream()
				.map((Airport airport)->modelMapper.map(airport, AirportDTO.class))
				.collect(Collectors.toList());
		
		if (airportList.size() == 0) {
			throw new AirportManagementException("No airport available for given airport name.");
		} else
			return airportList;
	}

	@Override
	public AirportDTO updateAirportDetails(AirportDTO airportDto) {
		// convert DTO to Entity
				Airport airportRequest=modelMapper.map(airportDto, Airport.class);
				if(airportRepository.existsById(airportRequest.getAirportId())) {
				Airport airport=airportRepository.save(airportRequest);
				// convert Entity to DTO 
						AirportDTO airportResponse=modelMapper.map(airport,AirportDTO.class);
		
	return airportResponse;
				}
				else {
					throw new AirportManagementException("No record present to update for given id to update.");

				}
}
}