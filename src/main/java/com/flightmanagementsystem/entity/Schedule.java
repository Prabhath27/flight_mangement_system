package com.flightmanagementsystem.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@Entity //The @Entity annotation indicates that this class is a JPA entity, representing a table in the database.
@Table(name="schedule") //The @Table annotation specifies the name of the table in the database.
public class Schedule {
	// Unique identifier for schedule
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer scheduleId;
	// Represents the source airport for this schedule
	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="safk1")
	private Airport sourceAirport;
	// Represents the destination airport for this schedule

	@Valid
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="safk2")
	private Airport destinationAirport;
	// The date when the flight is scheduled to arrive
	@NotNull(message = "Arrival Date should not be null")
	private LocalDate arrivalDate;
	// The date when the flight is scheduled to arrive
	@NotNull(message="Destination Date should not be null")
	private LocalDate departureDate;
	
	// Default constructor for the Schedule class
	public Schedule() {}

	// Constructor to initialize Schedule with details
	public Schedule(Integer scheduleId, Airport sourceAirport, Airport destinationAirport, LocalDate arrivalDate,
			LocalDate departureDate) {
		super();
		this.scheduleId = scheduleId;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}

	// Getter and setter methods are used to encapsulate access to the fields of the class.
	public Integer getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}

	public Airport getSourceAirport() {
		return sourceAirport;
	}

	public void setSourceAirport(Airport sourceAirport) {
		this.sourceAirport = sourceAirport;
	}

	public Airport getDestinationAirport() {
		return destinationAirport;
	}

	public void setDestinationAirport(Airport destinationAirport) {
		this.destinationAirport = destinationAirport;
	}

	public LocalDate getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(LocalDate arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public LocalDate getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	

	@Override
	// Override the toString() method to provide a string representation of the object.
	public String toString() {
		return "Schedule [scheduleId=" + scheduleId + ", sourceAirport=" + sourceAirport + ", destinationAirport="
				+ destinationAirport + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + "]";
	}	
		
	

}
