package com.flightmanagementsystem.dto;

import java.time.LocalDate;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public class ScheduleDTO {
	
	private Integer scheduleId;
	@Valid
	private AirportDTO sourceAirport;
	@Valid
	private AirportDTO destinationAirport;
	@NotNull(message = "Arrival Date should not be null")
	private LocalDate arrivalDate;
	@NotNull(message="Destination Date should not be null")
	private LocalDate departureDate;
	public ScheduleDTO(Integer scheduleId, AirportDTO sourceAirport, AirportDTO destinationAirport, LocalDate arrivalDate,
			LocalDate departureDate) {
		super();
		this.scheduleId = scheduleId;
		this.sourceAirport = sourceAirport;
		this.destinationAirport = destinationAirport;
		this.arrivalDate = arrivalDate;
		this.departureDate = departureDate;
	}
	public ScheduleDTO() {}
	public Integer getScheduleId() {
		return scheduleId;
	}
	public void setScheduleId(Integer scheduleId) {
		this.scheduleId = scheduleId;
	}
	public AirportDTO getSourceAirport() {
		return sourceAirport;
	}
	public void setSourceAirport(AirportDTO sourceAirport) {
		this.sourceAirport = sourceAirport;
	}
	public AirportDTO getDestinationAirport() {
		return destinationAirport;
	}
	public void setDestinationAirport(AirportDTO destinationAirport) {
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
	public String toString() {
		return "ScheduleDTO [scheduleId=" + scheduleId + ", sourceAirport=" + sourceAirport + ", destinationAirport="
				+ destinationAirport + ", arrivalDate=" + arrivalDate + ", departureDate=" + departureDate + "]";
	}
	
	

}
