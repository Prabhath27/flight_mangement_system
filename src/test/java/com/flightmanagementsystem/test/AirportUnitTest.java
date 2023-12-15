package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.flightmanagementsystem.dto.AirportDTO;
import com.flightmanagementsystem.entity.Airport;
import com.flightmanagementsystem.exception.AirportManagementException;
import com.flightmanagementsystem.repository.AirportRepository;
import com.flightmanagementsystem.service.AirportService;

@ExtendWith(MockitoExtension.class)
public class AirportUnitTest {
	 @Mock
	    private AirportRepository airportRepository;

	    @Mock
	    private ModelMapper modelMapper;

	    @InjectMocks
	    private AirportService airportService;

	    @Test
	    public void testAddAirportDetails() {
	        AirportDTO airportDTO = new AirportDTO(1, "AirportName", "AirportCity", "AirportCountry");
	        Airport airport = new Airport(1, "AirportName", "AirportCity", "AirportCountry");

	        when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airport);
	        when(airportRepository.save(airport)).thenReturn(airport);
	        when(modelMapper.map(airport, AirportDTO.class)).thenReturn(airportDTO);

	        AirportDTO result = airportService.addAirportDetails(airportDTO);

	        assertNotNull(result);
	        assertEquals(airportDTO.getAirportId(), result.getAirportId());
	        assertEquals(airportDTO.getAirportName(), result.getAirportName());
	        assertEquals(airportDTO.getAirportCity(), result.getAirportCity());
	        assertEquals(airportDTO.getAirportCountry(), result.getAirportCountry());
	    }
	    
	    @Test
	    public void testViewByCountry_WhenAirportsFound() {
	        String country = "TestCountry";
	        Airport airport1 = new Airport(1, "AirportName1", "City1", country);
	        Airport airport2 = new Airport(2, "AirportName2", "City2", country);
	        List<Airport> airports = List.of(airport1, airport2);

	        AirportDTO airportDto1 = new AirportDTO(1, "AirportName1", "City1", country);
	        AirportDTO airportDto2 = new AirportDTO(2, "AirportName2", "City2", country);
	        List<AirportDTO> expectedAirportDtoList = List.of(airportDto1, airportDto2);

	        when(airportRepository.findByAirportCountry(country)).thenReturn(airports);
	        when(modelMapper.map(airport1, AirportDTO.class)).thenReturn(airportDto1);
	        when(modelMapper.map(airport2, AirportDTO.class)).thenReturn(airportDto2);

	        List<AirportDTO> result = airportService.viewByCountry(country);

	        assertNotNull(result);
	        assertEquals(expectedAirportDtoList.size(), result.size());
	        assertEquals(expectedAirportDtoList, result);
	    }

	    @Test
	    public void testViewByCountry_WhenNoAirportsFound() {
	        String country = "NonExistentCountry";
	        when(airportRepository.findByAirportCountry(country)).thenReturn(new ArrayList<>());

	        try {
	            airportService.viewByCountry(country);
	            fail("Expected AirportManagementException was not thrown");
	        } catch (AirportManagementException e) {
	            assertEquals("No airport available in given country.", e.getMessage());
	        }
	    }
	    @Test
	    public void testViewAirportsWithData() {
	        // Mocking Airport and AirportDTO objects
	        Airport airport1 = new Airport(1, "AirportName", "AirportCity", "AirportCountry");
	        Airport airport2 = new Airport(2, "AirportName2", "AirportCity2", "AirportCountry2");

	        AirportDTO airportDTO1 = new AirportDTO(1, "AirportName", "AirportCity", "AirportCountry");
	        AirportDTO airportDTO2 = new AirportDTO(2, "AirportName2", "AirportCity2", "AirportCountry2");

	        // Mock behavior for airportRepository to return a list of airports
	        List<Airport> airports = List.of(airport1, airport2);
	        when(airportRepository.findAll()).thenReturn(airports);

	        // Mock behavior for modelMapper to map Airport to AirportDTO
	        when(modelMapper.map(airport1, AirportDTO.class)).thenReturn(airportDTO1);
	        when(modelMapper.map(airport2, AirportDTO.class)).thenReturn(airportDTO2);

	        // Perform the method call
	        List<AirportDTO> result = airportService.viewAirports();

	        // Verify that airportRepository.findAll() was called
	        verify(airportRepository).findAll();

	        // Verify that modelMapper.map() was called for each Airport object
	        verify(modelMapper).map(airport1, AirportDTO.class);
	        verify(modelMapper).map(airport2, AirportDTO.class);

	        // Verify the size of the result list
	        assertEquals(2, result.size());
	        // Optionally, add further assertions based on your implementation
	    }
	    
	    @Test
	    public void testUpdateAirportDetailsWithData() {
	        // Create a sample AirportDTO object
	        AirportDTO airportDTO = new AirportDTO(1, "AirportName", "AirportCity", "AirportCountry");
	        // Set properties for airportDTO if required

	        // Mock behavior for modelMapper to map AirportDTO to Airport and vice versa
	        Airport airportEntity = new Airport(1, "AirportName", "AirportCity", "AirportCountry");
	        when(modelMapper.map(airportDTO, Airport.class)).thenReturn(airportEntity);

	        Airport updatedAirportEntity = new Airport(1, "dhoniName", "AirportCity", "AirportCountry");
	        when(airportRepository.existsById(airportEntity.getAirportId())).thenReturn(true);
	        when(airportRepository.save(airportEntity)).thenReturn(updatedAirportEntity);

	        AirportDTO updatedAirportDTO = new AirportDTO(1, "dhoniName", "AirportCity", "AirportCountry");
	        when(modelMapper.map(updatedAirportEntity, AirportDTO.class)).thenReturn(updatedAirportDTO);

	        // Perform the method call
	        AirportDTO result = airportService.updateAirportDetails(airportDTO);

	        // Verify interactions and method calls
	        verify(modelMapper).map(airportDTO, Airport.class);
	        verify(airportRepository).existsById(airportEntity.getAirportId());
	        verify(airportRepository).save(airportEntity);
	        verify(modelMapper).map(updatedAirportEntity, AirportDTO.class);

	        // Assert the result or any other necessary verifications/assertions
	        // Example: Assert that the returned AirportDTO matches the expected updatedAirportDTO
	        assertEquals(updatedAirportDTO, result);
	    }

}
