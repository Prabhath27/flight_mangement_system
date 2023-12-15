package com.flightmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AirportDTO {
    private int airportId; // Airport ID attribute
    @NotBlank(message = "Airport name is required")
    @NotNull(message = "Airport not null")
    private String airportName; // Airport name attribute
    @NotBlank(message = "Airport city is required")
    private String airportCity; // Airport city attribute
    @NotBlank(message = "Airport country is required")
    private String airportCountry; // Airport country attribute

    // Constructor with parameters for AirportDTO
    public AirportDTO(int airportId, String airportName, String airportCity, String airportCountry) {
        super();
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
    }

    // Default constructor for AirportDTO
    public AirportDTO() {}

    // Getter for airportId
    public int getAirportId() {
        return airportId;
    }

    // Setter for airportId
    public void setAirportId(int airportId) {
        this.airportId = airportId;
    }

    // Getter for airportName
    public String getAirportName() {
        return airportName;
    }

    // Setter for airportName
    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    // Getter for airportCity
    public String getAirportCity() {
        return airportCity;
    }

    // Setter for airportCity
    public void setAirportCity(String airportCity) {
        this.airportCity = airportCity;
    }

    // Getter for airportCountry
    public String getAirportCountry() {
        return airportCountry;
    }

    // Setter for airportCountry
    public void setAirportCountry(String airportCountry) {
        this.airportCountry = airportCountry;
    }

    // Override toString method to display AirportDTO details
    @Override
    public String toString() {
        return "AirportDTO [airportId=" + airportId + ", airportName=" + airportName + ", airportCity=" + airportCity
                + ", airportCountry=" + airportCountry + "]";
    }
}
