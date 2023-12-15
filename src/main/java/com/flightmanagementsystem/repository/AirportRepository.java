package com.flightmanagementsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.Airport;



@Repository
public interface AirportRepository extends JpaRepository<Airport,Integer> {
    // Repository interface for performing database operations related to Airport entity
    // Method to find airports by country
    public List<Airport> findByAirportCountry(String country);

    // Method to find airports by city
    public List<Airport> findByAirportCity(String city);

    // Method to find airports by name
    public List<Airport> findByAirportName(String name);
}
