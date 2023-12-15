package com.flightmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.Airport;



@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer>
{
	public List<Airport> findByAirportCountry(String country);
	public List<Airport> findByAirportCity(String city);
	public List<Airport> findByAirportName(String name);
}
