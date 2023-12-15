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
public class Airport 
{
    @Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int airportId;
    @NotBlank(message="Airport name is required")
	@NotNull(message="Airport not null")
	private String airportName;
    @NotBlank(message="Airport city is required")
	private String airportCity;
    @NotBlank(message="Airport country is required")
	private String airportCountry;
	public Airport()
	{
	}
	public Airport(int airportId, String airportName, String airportCity, String airportCountry) 
	{
		super();
		this.airportId = airportId;
		this.airportName = airportName;
		this.airportCity = airportCity;
		this.airportCountry = airportCountry;

	}
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
		return "Airport [airportId=" + airportId + ", airportName=" + airportName + ", airportCity=" + airportCity
				+ ", airportCountry=" + airportCountry + "]";
	}

}
