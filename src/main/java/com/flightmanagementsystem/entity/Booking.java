package com.flightmanagementsystem.entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	@NotNull(message="Booking date is required")
	
	private LocalDate bookingDate;
	@Valid
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="bookfk")
	private List<Passenger> passengerList;
	@NotNull(message="Booking cost is required")
	private Double totalCost;
	@Valid
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="flightfk")
	private Flight flight;
	@NotNull(message="Number of passenger is required")
	@Min(value = 1, message = "Number of passengers must be at least 1")
	private Integer noOfPassengers;
	
	public Booking() {}
	public Booking(Integer bookingId, Passenger passenger, LocalDate bookingDate, List<Passenger> passengerList,
			Double totalCost, Flight flight, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.totalCost = totalCost;
		this.flight = flight;
		this.noOfPassengers = noOfPassengers;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public List<Passenger> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<Passenger> passengerList) {
		this.passengerList = passengerList;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	public Integer getNoOfPassengers() {
		return noOfPassengers;
	}
	public void setNoOfPassengers(Integer noOfPassengers) {
		this.noOfPassengers = noOfPassengers;
	}
	@Override
	public String toString() {
		return "Booking [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", passengerList=" + passengerList
				+ ", totalCost=" + totalCost + ", flight=" + flight + ", noOfPassengers=" + noOfPassengers + "]";
	}
		
	

}
