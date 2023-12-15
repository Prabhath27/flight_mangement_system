package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.flightmanagementsystem.dto.PassengerDTO;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.PassengerManagementException;
import com.flightmanagementsystem.repository.PassengerRepository;
import com.flightmanagementsystem.service.PassengerService;

@ExtendWith(MockitoExtension.class)
public class PassengerUnitTest {
	
	 @Mock
	    private PassengerRepository passengerRepository;

	    @Mock
	    private ModelMapper modelMapper;

	    @InjectMocks
	    private PassengerService passengerService;

	    private PassengerDTO passengerDTO;

	    @BeforeEach
	    public void setUp() {
	        passengerDTO = new PassengerDTO();
	        passengerDTO.setPassengerName("Test Passenger");
	        passengerDTO.setAge(25);
	        passengerDTO.setPassengerUIN(1234567890L);
	    }

	    @Test
	    public void testAddPassenger() {
	        Passenger passenger = new Passenger("Test Passenger", 25, 1234567890L);

	        when(modelMapper.map(passengerDTO, Passenger.class)).thenReturn(passenger);
	        when(passengerRepository.save(any(Passenger.class))).thenReturn(passenger);
	        when(modelMapper.map(passenger, PassengerDTO.class)).thenReturn(passengerDTO);

	        PassengerDTO result = passengerService.addPassenger(passengerDTO);

	        assertNotNull(result);
	        assertEquals("Test Passenger", result.getPassengerName());
	        assertEquals(25, result.getAge());
	        assertEquals(1234567890L, result.getPassengerUIN());
	    }

	    @Test
	    public void testViewAllPassenger_Success() {
	        List<Passenger> passengers = new ArrayList<>();
	        Passenger passenger1 = new Passenger("Passenger 1", 30, 1111111111L);
	        PassengerDTO passenger1dto=modelMapper.map(passenger1, PassengerDTO.class);
	        passengers.add(passenger1);

	        Passenger passenger2 = new Passenger("Passenger 2", 35, 2222222222L);
	        PassengerDTO passenger2dto=modelMapper.map(passenger2, PassengerDTO.class);
	        passengers.add(passenger2);

	        when(passengerRepository.findAll()).thenReturn(passengers);
	        
	         when(modelMapper.map(passenger1, PassengerDTO.class)).thenReturn(passenger1dto);
	        when(modelMapper.map(passenger2, PassengerDTO.class)).thenReturn(passenger2dto);

	        List<PassengerDTO> result = passengerService.viewAllPassenger();

	       assertNotNull(result);
	        assertEquals(2, result.size());
	       // assertEquals("Passenger 1", result.get(0).getPassengerName());
//	        assertEquals(35, result.get(1).getAge());
	    }

	    @Test
	    public void testViewAllPassenger_NoData() {
	        when(passengerRepository.findAll()).thenReturn(new ArrayList<>());

	        assertThrows(PassengerManagementException.class, () -> {
	            passengerService.viewAllPassenger();
	        });
	    }

	    @Test
	    public void testViewPassengerByUIN_ValidUIN() {
	        Long validUIN = 1234567890L;
	        Passenger passenger = new Passenger("Test Passenger", 25, validUIN);
	        PassengerDTO passengerdto = new PassengerDTO("Test Passenger", 25, validUIN);
	        

	        when(passengerRepository.findByPassengerUIN(validUIN)).thenReturn(passenger);
	        when(modelMapper.map(passenger, PassengerDTO.class)).thenReturn(passengerdto);

	        PassengerDTO result = passengerService.viewPassengerByUIN(validUIN);

	       assertNotNull(result);
	   assertEquals("Test Passenger", result.getPassengerName());
	    assertEquals(25, result.getAge());
	    assertEquals(validUIN, result.getPassengerUIN());
	    }

	    @Test
	    public void testViewPassengerByUIN_InvalidUIN() {
	        Long invalidUIN = 9876543210L;
	        when(passengerRepository.findByPassengerUIN(invalidUIN)).thenReturn(null);

	        assertThrows(PassengerManagementException.class, () -> {
	            passengerService.viewPassengerByUIN(invalidUIN);
	        });
	    }

	    @Test
	    public void testViewPassengerByMobileNo_ValidMobileNo() {
	        String validMobileNo = "1234567890";
	        Passenger passenger = new Passenger("Test Passenger", 25, 1234567890L);
	        PassengerDTO passengerdto = new PassengerDTO("Test Passenger", 25, 1234567890L);

	        when(passengerRepository.findByMobileNumber(validMobileNo)).thenReturn(passenger);
	       when(modelMapper.map(passenger, PassengerDTO.class)).thenReturn(passengerdto);

	        PassengerDTO result = passengerService.viewPassengerByMobileNo(validMobileNo);

	        assertNotNull(result);
	        assertEquals("Test Passenger", result.getPassengerName());
	        assertEquals(25, result.getAge());
	        assertEquals(1234567890L, result.getPassengerUIN());
	    }

	    @Test
	    public void testViewPassengerByMobileNo_InvalidMobileNo() {
	        String invalidMobileNo = "9876543210";
	        when(passengerRepository.findByMobileNumber(invalidMobileNo)).thenReturn(null);

	        assertThrows(PassengerManagementException.class, () -> {
	            passengerService.viewPassengerByMobileNo(invalidMobileNo);
	        });
	}

}
