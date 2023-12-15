package com.flightmanagementsystem.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.flightmanagementsystem.dto.BookingDTO;
import com.flightmanagementsystem.service.BookingService;

import jakarta.validation.Valid;

@RestController
public class BookingController {
	
	@Autowired
	public BookingService bookingService;
	@PostMapping("addBooking")
	public String addBooking(@Valid @RequestBody BookingDTO bookingDto) {
		
		bookingService.addBooking(bookingDto);
		return "ticket booked";
	}
	
	@DeleteMapping("/cancelBooking/{bookingid}")
	public String deleteBooking(@PathVariable("bookingid") Integer bookingid ) {
		BookingDTO bookingDto=bookingService.cancelBooking(bookingid);
		if(bookingDto==null)
		return "ticket is not booked to cancel";
	else {
		return "cancellation done";
	}
}
	@GetMapping("/viewbookings")
	public List<BookingDTO> viewBookings(){
		return bookingService.viewBookings();
	}
	
	@GetMapping("/viewByBookingId/{bookingid1}")
	public BookingDTO viewBookingByBookingId(@PathVariable("bookingid1") Integer bookingid){
		
		return bookingService.viewBookingByBookingId(bookingid);
	}
	
	@GetMapping("/viewByBookingDate/{bookdate}")
	public List<BookingDTO> viewBookingByDate(@PathVariable("bookdate") LocalDate bookingdate) {
		
		return bookingService.viewBookingByDate(bookingdate);
	}
	
	@GetMapping("/viewByPassengerId/{passengerid}")
	public List<BookingDTO> viewBookingByPassengerId(@PathVariable("passengerid") Integer passengerId) {
		return bookingService.viewBookingByPassengerId(passengerId);
	}
	
	@GetMapping("/viewBookingsByFlightId/{flightid}")
	public List<BookingDTO>viewBookingByFlightId(@PathVariable("flightid")Integer flightid){
		return bookingService.viewBookingsByFlightId(flightid);
	}
	
	@PutMapping("/updateBooking")
	public String updateDetails(@Valid @RequestBody BookingDTO bookingid) {
		BookingDTO bookingDto=bookingService.updateBooking(bookingid);
		if(bookingDto==null) {
		return "not updated";
	}
		else {
			return "updated";
		}
	

}
}
