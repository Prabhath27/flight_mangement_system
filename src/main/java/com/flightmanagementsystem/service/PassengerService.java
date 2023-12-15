package com.flightmanagementsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.PassengerDao;
import com.flightmanagementsystem.dto.PassengerDTO;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.PassengerManagementException;
import com.flightmanagementsystem.repository.PassengerRepository;

//The @Service annotation indicates that this class is a service component in the Spring framework.

@Service
public class PassengerService implements PassengerDao
{
  // The @Autowired annotation injects the PassengerRepository and ModelMapper beans into this service.

	@Autowired
	private PassengerRepository passengerRepository;
  // ModelMapper is a library for mapping objects, used for converting between DTO and Entity objects.

	@Autowired
	public ModelMapper modelMapper; 
	@Override
	public PassengerDTO addPassenger(PassengerDTO passengerDto) 
	{
      // Method to add a new passenger by converting a PassengerDTO to an Entity and saving it to the repository.

		Passenger passengerRequest=modelMapper.map(passengerDto, Passenger.class);
		 Passenger passenger=passengerRepository.save(passengerRequest);
		 PassengerDTO passengerResponse=modelMapper.map(passenger, PassengerDTO.class);
		 return passengerResponse;
	}

	@Override
	public List<PassengerDTO> viewAllPassenger()
	{
      // Method to view all passengers by converting Entity objects to DTOs.
		List<PassengerDTO> passengerResponse=passengerRepository.findAll().stream()
				.map((Passenger passenger)->modelMapper.map(passenger,PassengerDTO.class))
				.collect(Collectors.toList());
		if(passengerResponse.size()==0) {
			throw new PassengerManagementException("No data in the list");
			}
		else {
			return passengerResponse;
			}
	}

	@Override
	public PassengerDTO viewPassengerByUIN(Long uin) {
      // Method to view a passenger by UIN (Unique Identification Number).
		if(passengerRepository.findByPassengerUIN(uin)==null) {
			throw new PassengerManagementException("Invlaid UIN.");
			}
		else {
		PassengerDTO passengerDto=modelMapper.map(passengerRepository.findByPassengerUIN(uin),PassengerDTO.class);
		  return passengerDto;
		}
	}

	@Override
	public PassengerDTO viewPassengerByMobileNo(String contactNo)
	{
      // Method to view a passenger by Mobile Number.

		if(passengerRepository.findByMobileNumber(contactNo)==null) {
			throw new PassengerManagementException("Invlaid Contact number.");
			}
		else {
		PassengerDTO passengerDto=modelMapper.map(passengerRepository.findByMobileNumber(contactNo),PassengerDTO.class);
		  return passengerDto;
	}

}
}