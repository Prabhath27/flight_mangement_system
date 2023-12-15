package com.flightmanagementsystem.dao;

import java.time.LocalDate;
import java.util.List;

import com.flightmanagementsystem.dto.BookingDTO;


public interface BookingDao {
	public BookingDTO addBooking(BookingDTO bookingDto);

	public BookingDTO cancelBooking(Integer bookingid);

	public List<BookingDTO> viewBookings();

	public List<BookingDTO> viewBookingByPassengerId(Integer passengerId);

	public BookingDTO viewBookingByBookingId(Integer bookingid);

	public List<BookingDTO> viewBookingByDate(LocalDate bookingdate);

	public List<BookingDTO> viewBookingsByFlightId(Integer flightid);

	public BookingDTO updateBooking(BookingDTO bookingDto);

}
