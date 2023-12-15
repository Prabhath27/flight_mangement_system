package com.flightmanagementsystem.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class FlightDTO {
	
	private Integer flightId;
	@NotBlank(message = "Flight name must not be null")
	private String flightName;
	@NotNull(message = "Seat capacity must not be null")
	@Min(value = 10,message = "Seat capacity should be minimum 10")
	private Integer seatCapacity;
	@NotNull(message = "Fare must not be null")
	@Min(value=1,message = "Flight fare should be minimum 1")
	private Double fare; // cost per seat
	@Valid
	private List<ScheduleDTO> schedules;
	
	public FlightDTO(Integer flightId, String flightName, Integer seatCapacity, Double fare, List<ScheduleDTO> schedules) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.seatCapacity = seatCapacity;
		this.fare = fare;
		this.schedules = schedules;
	}
	public FlightDTO() {}
	public Integer getFlightId() {
		return flightId;
	}
	public void setFlightId(Integer flightId) {
		this.flightId = flightId;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public Integer getSeatCapacity() {
		return seatCapacity;
	}
	public void setSeatCapacity(Integer seatCapacity) {
		this.seatCapacity = seatCapacity;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public List<ScheduleDTO> getSchedules() {
		return schedules;
	}
	public void setSchedules(List<ScheduleDTO> schedules) {
		this.schedules = schedules;
	}
	@Override
	public String toString() {
		return "FlightDTO [flightId=" + flightId + ", flightName=" + flightName + ", seatCapacity=" + seatCapacity
				+ ", fare=" + fare + ", schedules=" + schedules + "]";
	}
	
	

}
