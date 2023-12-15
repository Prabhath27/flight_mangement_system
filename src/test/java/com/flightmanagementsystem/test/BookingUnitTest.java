package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.flightmanagementsystem.dto.BookingDTO;
import com.flightmanagementsystem.entity.Booking;
import com.flightmanagementsystem.exception.BookingManagementException;
import com.flightmanagementsystem.repository.BookingRepository;
import com.flightmanagementsystem.service.BookingService;

@ExtendWith(MockitoExtension.class)
public class BookingUnitTest {
	
	 @Mock
	    private BookingRepository bookingRepository;

	    @Mock
	    private ModelMapper modelMapper;

	    @InjectMocks
	    private BookingService bookingService;

	    @Test
	    public void testAddBooking() {
	        BookingDTO bookingDTO = new BookingDTO();
	        // Populate bookingDTO with necessary fields

	        Booking booking = new Booking();
	        // Populate booking entity with necessary fields

	        when(modelMapper.map(bookingDTO, Booking.class)).thenReturn(booking);
	        when(bookingRepository.save(any(Booking.class))).thenReturn(booking);
	        when(modelMapper.map(booking, BookingDTO.class)).thenReturn(bookingDTO);

	        BookingDTO result = bookingService.addBooking(bookingDTO);

	        assertNotNull(result);
	        // Assertions for the expected result based on input data
	    }

	    // Add similar tests for other methods like cancelBooking, viewBookings, viewBookingByBookingId, 
	    // viewBookingByDate, viewBookingByPassengerId, viewBookingsByFlightId, and updateBooking
	    @Test
	    public void testCancelBooking_ExistingBookingId() {
	        // Mock data
	        Integer bookingId = 1;
	        Booking booking = new Booking();
	        // Populate booking entity with necessary fields

	        BookingDTO bookingDTO = new BookingDTO();
	        // Populate bookingDTO with necessary fields

	        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(booking));
	        when(modelMapper.map(booking, BookingDTO.class)).thenReturn(bookingDTO);

	        BookingDTO result = bookingService.cancelBooking(bookingId);

	        assertNotNull(result);
	        // Add assertions for the expected result based on input data
	    }

	    @Test
	    public void testCancelBooking_NonExistingBookingId() {
	        // Mock data for a non-existing booking ID
	        Integer bookingId = 2;

	        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());

	        assertThrows(BookingManagementException.class, () -> {
	            bookingService.cancelBooking(bookingId);
	        });
	    }
	    
	    @Test
	    public void testViewBookings_WithBookings() {
	        List<Booking> mockBookingList = new ArrayList<>();
	        mockBookingList.add(new Booking());
	        // Add more mocked Booking objects if needed

	        when(bookingRepository.findAll()).thenReturn(mockBookingList);

	        List<BookingDTO> mockedDTOList = mockBookingList.stream()
	                .map(booking -> modelMapper.map(booking, BookingDTO.class))
	                .collect(Collectors.toList());

	        when(modelMapper.map(any(Booking.class), eq(BookingDTO.class)))
	                .thenReturn(new BookingDTO()); // Assuming a generic BookingDTO

	        List<BookingDTO> result = bookingService.viewBookings();

	        assertNotNull(result);
	        assertFalse(result.isEmpty());
	        assertEquals(mockedDTOList.size(), result.size());
	    }

	    @Test
	    public void testViewBookings_NoBookings() {
	        when(bookingRepository.findAll()).thenReturn(new ArrayList<>());

	        assertThrows(BookingManagementException.class, () -> {
	            bookingService.viewBookings();
	        });
	    }

}
