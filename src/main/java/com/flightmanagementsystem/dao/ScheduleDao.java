package com.flightmanagementsystem.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.flightmanagementsystem.dto.ScheduleDTO;


public interface ScheduleDao {
	public ScheduleDTO addSchedule(ScheduleDTO scheduleDto); // Adds a schedule

	public ScheduleDTO updateSchedule(ScheduleDTO scheduleDto); // Updates a schedule

	public List<ScheduleDTO> viewSchedules(); // Views all schedules

	// Views schedules by source and destination
	public List<ScheduleDTO> viewBySourceAndDestination(String source, String destination); 

	public List<ScheduleDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate); // Views schedules by source, destination, and departure date

	public List<ScheduleDTO> viewByDepartureTime(LocalDate date); // Views schedules by departure time
	

}
