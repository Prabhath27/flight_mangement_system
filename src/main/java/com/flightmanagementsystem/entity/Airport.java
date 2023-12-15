package com.flightmanagementsystem.entity;
 
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(name="AirportTable")
public class Airport {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int airportId; // Airport ID attribute

    @NotBlank(message="Airport name is required")
    @NotNull(message="Airport not null")
    private String airportName; // Airport name attribute

    @NotBlank(message="Airport city is required")
    private String airportCity; // Airport city attribute

    @NotBlank(message="Airport country is required")
    private String airportCountry; // Airport country attribute

    // Default constructor for Airport
    public Airport() {}

    // Constructor with parameters for Airport
    public Airport(int airportId, String airportName, String airportCity, String airportCountry) {
        super();
        this.airportId = airportId;
        this.airportName = airportName;
        this.airportCity = airportCity;
        this.airportCountry = airportCountry;
    }

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

    // Override toString method to display Airport details
    @Override
    public String toString() {
        return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", airportCity=" + airportCity
                + ", airportCountry=" + airportCountry + "]";
    }
}
