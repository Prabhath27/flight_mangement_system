package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.flightmanagementsystem.dto.FlightDTO;
import com.flightmanagementsystem.dto.ScheduleDTO;
import com.flightmanagementsystem.entity.Flight;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.repository.FlightRepository;
import com.flightmanagementsystem.service.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightUnitTest {
	
	@InjectMocks
    private FlightService flightService;
	@Mock
    private FlightRepository flightRepository;
	@Mock
    private ModelMapper modelMapper;
	
	@Test
    public void testAddFlight() {
		
		FlightDTO flightDto = new FlightDTO(1, "Flight1", 150, 100.0, List.of(new ScheduleDTO()));

        Flight flightEntity = new Flight(1, "Flight1", 150, 100.0, List.of(new Schedule()));

        when(modelMapper.map(flightDto, Flight.class)).thenReturn(flightEntity);
        when(flightRepository.save(flightEntity)).thenReturn(flightEntity);
        when(modelMapper.map(flightEntity, FlightDTO.class)).thenReturn(flightDto);

        // When
        FlightDTO result = flightService.addFlight(flightDto);

        // Then
        assertEquals(flightDto.getFlightName(), result.getFlightName());
        assertEquals(flightDto.getSeatCapacity(), result.getSeatCapacity());
        assertEquals(flightDto.getFare(), result.getFare());
        // Add more assertions based on your requirements
    }
	@Test
    public void testViewByFlightIdWithData() {
        int flightId = 123; 
        FlightDTO flightDto = new FlightDTO(123, "Flight1", 150, 100.0, List.of(new ScheduleDTO()));

        Flight flightEntity = new Flight(123, "Flight1", 150, 100.0, List.of(new Schedule()));

        // Mock behavior for flightRepository.existsById
        when(flightRepository.existsById(flightId)).thenReturn(true);

        when(flightRepository.findById(flightId)).thenReturn(Optional.of(flightEntity));

        // Mock behavior for modelMapper.map
        FlightDTO flightDTO = new FlightDTO(/* Set properties for FlightDTO */);
        when(modelMapper.map(flightEntity, FlightDTO.class)).thenReturn(flightDTO);

        // Perform the method cal
        FlightDTO result = flightService.viewByFlightId(flightId);

        // Verify interactions and method calls
        verify(flightRepository).existsById(flightId);
        verify(flightRepository).findById(flightId);
        verify(modelMapper).map(flightEntity, FlightDTO.class);

        // Assert the result or any other necessary verifications/assertions
        // Example: Assert that the returned FlightDTO matches the expected flightDTO
        assertEquals(flightDTO, result);
    }
	
	
	@Test
    public void testViewByDepartureDateWithMatchingData() {
        LocalDate date = LocalDate.now(); // Provide a departure date for testing

        // Mock behavior for flightRepository.findAll
        List<Flight> mockFlightList = new ArrayList<>();
        // Create a Flight object that matches the criteria
        Flight matchingFlight = new Flight(/* Set properties for matching flight */);
        Schedule matchingSchedule = new Schedule();
        matchingSchedule.setDepartureDate(date);
        matchingFlight.setSchedules(List.of(matchingSchedule));
        mockFlightList.add(matchingFlight);

        when(flightRepository.findAll()).thenReturn(mockFlightList);

        // Mock behavior for modelMapper.map
        FlightDTO matchingFlightDTO = new FlightDTO(/* Set properties for matching flight DTO */);
        when(modelMapper.map(matchingFlight, FlightDTO.class)).thenReturn(matchingFlightDTO);

        // Perform the method call
        List<FlightDTO> result = flightService.viewByDepartureDate(date);

        // Verify interactions and method calls
        verify(flightRepository).findAll();
        verify(modelMapper).map(matchingFlight, FlightDTO.class);

        // Assert the result or any other necessary verifications/assertions
        assertEquals(1, result.size());
        assertEquals(matchingFlightDTO, result.get(0));
    }

}
