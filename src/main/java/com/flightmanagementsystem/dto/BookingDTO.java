package com.flightmanagementsystem.dto;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class BookingDTO {
private Integer bookingId;
@NotNull(message="Booking date is required")
private LocalDate bookingDate;
@Valid
private List<PassengerDTO> passengerList;
@NotNull(message="Booking cost is required")
private Double totalCost;
@Valid
private FlightDTO flight;
@NotNull(message="Number of passenger is required")
@Min(value = 1, message = "Number of passengers must be at least 1")
private Integer noOfPassengers;
	public BookingDTO(Integer bookingId, LocalDate bookingDate, List<PassengerDTO> passengerList, Double totalCost,
			FlightDTO flight, Integer noOfPassengers) {
		super();
		this.bookingId = bookingId;
		this.bookingDate = bookingDate;
		this.passengerList = passengerList;
		this.totalCost = totalCost;
		this.flight = flight;
		this.noOfPassengers = noOfPassengers;
	}
	public BookingDTO() {}
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
	public List<PassengerDTO> getPassengerList() {
		return passengerList;
	}
	public void setPassengerList(List<PassengerDTO> passengerList) {
		this.passengerList = passengerList;
	}
	public Double getTotalCost() {
		return totalCost;
	}
	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}
	public FlightDTO getFlight() {
		return flight;
	}
	public void setFlight(FlightDTO flight) {
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
		return "BookingDTO [bookingId=" + bookingId + ", bookingDate=" + bookingDate + ", passengerList="
				+ passengerList + ", totalCost=" + totalCost + ", flight=" + flight + ", noOfPassengers="
				+ noOfPassengers + "]";
	}
	
	

}
