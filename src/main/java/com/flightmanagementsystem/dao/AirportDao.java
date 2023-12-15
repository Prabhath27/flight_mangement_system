package com.flightmanagementsystem.dao;

import java.util.List;

import com.flightmanagementsystem.dto.AirportDTO;


public interface AirportDao {
	
	public AirportDTO addAirportDetails(AirportDTO airport);
	 
    public List<AirportDTO> viewAirports();
 
    public List<AirportDTO> viewByCountry(String country);
 
    public List<AirportDTO> viewByCity(String city);
 
    public List<AirportDTO> viewByAirportName(String airportName);
 
    public AirportDTO updateAirportDetails(AirportDTO airport);

}
