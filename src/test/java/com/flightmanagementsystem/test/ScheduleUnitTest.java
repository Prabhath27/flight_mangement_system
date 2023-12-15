package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.flightmanagementsystem.dto.AirportDTO;
import com.flightmanagementsystem.dto.ScheduleDTO;
import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.exception.ScheduleManagementException;
import com.flightmanagementsystem.repository.ScheduleRepository;
import com.flightmanagementsystem.service.ScheduleService;

@ExtendWith(MockitoExtension.class)
public class ScheduleUnitTest {

	 @Mock
	    private ScheduleRepository scheduleRepository;

	    @Mock
	    private ModelMapper modelMapper;

	    @InjectMocks
	    private ScheduleService scheduleService;

	    @Test
	    public void testAddSchedule() {
	        // Prepare test data
	        ScheduleDTO scheduleDto = new ScheduleDTO(1, new AirportDTO(1,"shamshbad","hyd","india"), new AirportDTO(2,"chennai","TN","india"),
	                LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 16));
	        Schedule schedule = new Schedule(1, new Airport(1,"shamshbad","hyd","india"), new Airport(2,"chennai","TN","india"),
	                LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 16));

	        when(modelMapper.map(scheduleDto, Schedule.class)).thenReturn(schedule);
	        when(scheduleRepository.save(schedule)).thenReturn(schedule);
	        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDto);

	        ScheduleDTO result = scheduleService.addSchedule(scheduleDto);

	        assertNotNull(result);
	        assertEquals(scheduleDto, result);
	    }

	    @Test
	    public void testUpdateSchedule_WhenExists() {
	        // Prepare test data
	        ScheduleDTO scheduleDto = new ScheduleDTO(1, new AirportDTO(1,"mumbai","hyd","india"), new AirportDTO(2,"chennai","TN","india"),
	                LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 16));
	        Schedule schedule = new Schedule(1, new Airport(1,"mumbai","hyd","india"), new Airport(2,"chennai","TN","india"),
	                LocalDate.of(2023, 12, 15), LocalDate.of(2023, 12, 16));

	        when(modelMapper.map(scheduleDto, Schedule.class)).thenReturn(schedule);
	        when(scheduleRepository.existsById(scheduleDto.getScheduleId())).thenReturn(true);
	        when(scheduleRepository.save(schedule)).thenReturn(schedule);
	        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDto);

	        ScheduleDTO result = scheduleService.updateSchedule(scheduleDto);

	        assertNotNull(result);
	        assertEquals(scheduleDto, result);
	    }
	   
	    @Test
	    public void testViewBySourceAndDestination_ValidSourceAndDestination() {
	        // Prepare test data
	        String source = "SourceAirport";
	        String destination = "DestinationAirport";
	        Airport sourceAirport = new Airport(1, "SourceAirport", "CityA", "CountryA");
	        Airport destinationAirport = new Airport(2, "DestinationAirport", "CityB", "CountryB");
	        Schedule schedule1 = new Schedule(1, sourceAirport, destinationAirport, null, null);
	        Schedule schedule2 = new Schedule(2, sourceAirport, destinationAirport, null, null);
	        List<Schedule> schedules = new ArrayList<>();
	        schedules.add(schedule1);
	        schedules.add(schedule2);

	        when(scheduleRepository.findAll()).thenReturn(schedules);
	        when(modelMapper.map(any(Schedule.class), eq(ScheduleDTO.class))).thenReturn(new ScheduleDTO());

	        List<ScheduleDTO> result = scheduleService.viewBySourceAndDestination(source, destination);

	        assertNotNull(result);
	        assertEquals(2, result.size()); // Assuming both schedules match the criteria
	    }

	    @Test
	    public void testViewBySourceAndDestination_InvalidSourceOrDestination() {
	         //Prepare test data
	        String source = "InvalidSource";
	        String destination = "InvalidDestination";
	       // List<Schedule> schedules = new ArrayList<>();
	        Airport sourceAirport = new Airport(1, "SourceAirport", "CityA", "CountryA");
	        Airport destinationAirport = new Airport(2, "DestinationAirport", "CityB", "CountryB");
	        Schedule schedule1 = new Schedule(1, sourceAirport, destinationAirport, null, null);
	        Schedule schedule2 = new Schedule(2, sourceAirport, destinationAirport, null, null);
	        List<Schedule> schedules1 = new ArrayList<>();
	        schedules1.add(schedule1);
	        schedules1.add(schedule2);
	        when(scheduleRepository.findAll()).thenReturn(schedules1);

	        assertThrows(ScheduleManagementException.class,
	                () -> scheduleService.viewBySourceAndDestination(source, destination));
	    }

}
