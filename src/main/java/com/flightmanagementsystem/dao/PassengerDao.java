package com.flightmanagementsystem.dao;

import java.util.List;

import com.flightmanagementsystem.dto.PassengerDTO;

public interface PassengerDao {
	public PassengerDTO addPassenger(PassengerDTO passengerDto);

	public List<PassengerDTO> viewAllPassenger();

	public PassengerDTO viewPassengerByUIN(Long uin);

	public PassengerDTO viewPassengerByMobileNo(String contactNo);

	
}
