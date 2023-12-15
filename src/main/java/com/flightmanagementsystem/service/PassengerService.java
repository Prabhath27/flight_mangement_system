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

@Service
public class PassengerService implements PassengerDao {
	
	@Autowired
	public PassengerRepository passengerRepository;
	@Autowired
	public ModelMapper modelMapper; 

	@Override
	public PassengerDTO addPassenger(PassengerDTO passengerDto) {
		Passenger passengerRequest=modelMapper.map(passengerDto, Passenger.class);
		 Passenger passenger=passengerRepository.save(passengerRequest);
		 PassengerDTO passengerResponse=modelMapper.map(passenger, PassengerDTO.class);
		 return passengerResponse;
	}

	@Override
	public List<PassengerDTO> viewAllPassenger(){
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		if(passengerRepository.findByPassengerUIN(uin)==null) {
			throw new PassengerManagementException("Invlaid UIN.");
			}
		else {
		
		PassengerDTO passengerDto=modelMapper.map(passengerRepository.findByPassengerUIN(uin),PassengerDTO.class);
		  return passengerDto;
		}
	}

	@Override
	public PassengerDTO viewPassengerByMobileNo(String contactNo){
		if(passengerRepository.findByMobileNumber(contactNo)==null) {
			throw new PassengerManagementException("Invlaid Contact number.");
			}
		else {
		PassengerDTO passengerDto=modelMapper.map(passengerRepository.findByMobileNumber(contactNo),PassengerDTO.class);
		  return passengerDto;
	}

}
}