package com.flightmanagementsystem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.ScheduleDao;
import com.flightmanagementsystem.dto.ScheduleDTO;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.ScheduleManagementException;
import com.flightmanagementsystem.repository.ScheduleRepository;

@Service // Indicates this class is a service managed by Spring.
public class ScheduleService implements ScheduleDao {
	
	@Autowired // Injects ScheduleRepository dependency.
	private ScheduleRepository scheduleRepository; // Repository for Schedule entity.

	@Autowired // Injects ModelMapper dependency.
	private ModelMapper modelMapper;

	
	@Override // Method to add a schedule.
	public ScheduleDTO addSchedule(ScheduleDTO scheduleDto) {
		
		//converting DTO to Entity
		Schedule scheduleRequest = modelMapper.map(scheduleDto, Schedule.class);
		Schedule schedule =scheduleRepository.save(scheduleRequest);
		//converting Entity to DTO
		ScheduleDTO scheduleResponse = modelMapper.map(schedule, ScheduleDTO.class); 
		return scheduleResponse;
	}

	@Override // Method to add a schedule.
	public ScheduleDTO updateSchedule(ScheduleDTO scheduleDto){
		        //converting DTO to Entity
				Schedule scheduleRequest = modelMapper.map(scheduleDto, Schedule.class);
				if(scheduleRepository.existsById(scheduleRequest.getScheduleId())) {
				Schedule schedule =scheduleRepository.save(scheduleRequest);
				//converting Entity to DTO
				ScheduleDTO scheduleResponse = modelMapper.map(schedule, ScheduleDTO.class); 
				return scheduleResponse;
	}
				else {
					// Throws an exception if the schedule does not exist.
					throw new ScheduleManagementException("no record present for given for id to update ");
					
				}
				}
	

	@Override
	public List<ScheduleDTO> viewSchedules(){
		// Retrieves all schedules and maps them to DTOs
		
		List<ScheduleDTO> scheduleResponse= scheduleRepository.findAll().stream()
				.map((Schedule schedule)->modelMapper.map(schedule, ScheduleDTO.class))
				.collect(Collectors.toList());
		// Throws an exception if no schedules are found; otherwise, returns the list of schedules
		if(scheduleResponse.size()==0) {
			throw new ScheduleManagementException("no Schedules in the list");}
		else {
			return scheduleResponse;
		}
	}

	@Override
	public List<ScheduleDTO> viewBySourceAndDestination(String source, String destination){
		// Filters schedules based on source and destination airports, mapping results to DTOs
		
		List<Schedule> schedule=scheduleRepository.findAll().stream()
				.filter(e -> e.getSourceAirport().getAirportName().equals(source)
						&& e.getDestinationAirport().getAirportName().equals(destination))
				.collect(Collectors.toList());
		
		// Throws an exception if no valid source or destination; otherwise, returns the filtered schedules
		List<ScheduleDTO> scheduleResponse=schedule.stream()
				.map((Schedule schedule1)->modelMapper.map(schedule1, ScheduleDTO.class))
				.collect(Collectors.toList());
		
		if (scheduleResponse.size() == 0) {
			throw new ScheduleManagementException("Invalid source or invalid destination");
		} else {
			return scheduleResponse;
		}
	}

	@Override
	public List<ScheduleDTO> viewBySourceDestinationAndDepartureDate(String source, String destination,
			LocalDate departureDate) {
		// Filters schedules by source, destination, and departure date, mapping results to DTOs

		List<ScheduleDTO> scheduleResponse = this.viewBySourceAndDestination(source, destination).stream()
				.filter(e -> e.getDepartureDate().isEqual(departureDate)).collect(Collectors.toList());
		
		// Throws an exception if no schedule matches the given criteria; otherwise, returns the filtered schedules
		if (scheduleResponse.size() == 0) {
			throw new ScheduleManagementException("No schedule for give source ,dstination and date.");
		} else {
			return scheduleResponse;
		}
	}

	@Override
	public List<ScheduleDTO> viewByDepartureTime(LocalDate date)throws ScheduleManagementException {
		// Filters schedules by departure date, mapping results to DTOs
		List<Schedule> schedule=scheduleRepository.findAll().stream()
				.filter(e -> e.getDepartureDate().isEqual(date)).collect(Collectors.toList());
		
		// Throws an exception if no schedules are found for the given date; otherwise, returns the filtered schedules
		List<ScheduleDTO> scheduleResponse=schedule.stream()
				.map((Schedule schedule1)->modelMapper.map(schedule1, ScheduleDTO.class))
				.collect(Collectors.toList());
		if (scheduleResponse.size()==0) {
			throw new ScheduleManagementException("No schedule for given date.");
		} else {
			return scheduleResponse;
		}
		
		
	}

}
