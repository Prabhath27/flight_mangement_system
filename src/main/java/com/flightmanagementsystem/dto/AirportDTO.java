package com.flightmanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AirportDTO {
	private int airportId;
	@NotBlank(message="Airport name is required")
	@NotNull(message="Airport not null")
	private String airportName;
	@NotBlank(message="Airport city is required")
	private String airportCity;
	@NotBlank(message="Airport country is required")
	private String airportCountry;
	public AirportDTO(int airportId, String airportName, String airportCity, String airportCountry) {
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportCountry = airportCountry;
	}
	public AirportDTO() {}
	public int getAirportId() {
		return airportId;
	}
	public void setAirportId(int airportId) {
		this.airportId = airportId;
	}
	public String getAirportName() {
		return airportName;
	}
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}
	public String getAirportCity() {
		return airportCity;
	}
	public void setAirportCity(String airportCity) {
		this.airportCity = airportCity;
	}
	public String getAirportCountry() {
		return airportCountry;
	}
	public void setAirportCountry(String airportCountry) {
		this.airportCountry = airportCountry;
	}
	@Override
	public String toString() {
		return "AirportDTO [airportId=" + airportId + ", airportName=" + airportName + ", airportCity=" + airportCity
				+ ", airportCountry=" + airportCountry + "]";
	}
	
	
	

}
