package com.flightmanagementsystem.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="flight")
public class Flight {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="fsfk")
	private List<Schedule> schedules;
	
	public Flight() {}

	public Flight(Integer flightId, String flightName, Integer seatCapacity, Double fare, List<Schedule> schedules) {
		super();
		this.flightId = flightId;
		this.flightName = flightName;
		this.seatCapacity = seatCapacity;
		this.fare = fare;
		this.schedules = schedules;
	}

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

	public List<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<Schedule> schedules) {
		this.schedules = schedules;
	}

	@Override
	public String toString() {
		return "Flight [flightId=" + flightId + ", flightName=" + flightName + ", seatCapacity=" + seatCapacity
				+ ", fare=" + fare + ", schedules=" + schedules + "]";
	}
		
	

}
