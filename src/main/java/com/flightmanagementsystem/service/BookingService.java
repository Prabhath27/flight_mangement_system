 package com.flightmanagementsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightmanagementsystem.dao.BookingDao;
import com.flightmanagementsystem.dto.BookingDTO;
import com.flightmanagementsystem.entity.Booking;
import com.flightmanagementsystem.entity.Passenger;
import com.flightmanagementsystem.exception.BookingManagementException;
import com.flightmanagementsystem.repository.BookingRepository;

//Service annotation indicates that this class is a service and should be picked up by Spring component scanning.

@Service
public class BookingService implements BookingDao
{

  // Autowired annotation injects the BookingRepository bean into this field.
  @Autowired
	private BookingRepository bookingRepository;
  // Autowired annotation injects the ModelMapper bean into this field.
  @Autowired
	private ModelMapper modelMapper;
  // Method to add a booking by converting DTO to Entity and then saving it.
  @Override
	public BookingDTO addBooking(BookingDTO bookingDto) {
		// convert DTO to Entity
		Booking bookingRequest=modelMapper.map(bookingDto, Booking.class);
		Booking booking= bookingRepository.save(bookingRequest);
	// convert Entity to DTO 
		BookingDTO bookingResponse=modelMapper.map(booking,BookingDTO.class);
		return bookingResponse;	
	}

  // Method to cancel a booking by bookingId.
  @Override
	public BookingDTO cancelBooking(Integer bookingid){
		// TODO Auto-generated method stub
		BookingDTO bookingResponse = null;
      // Check if the booking with the given id exists.
      if(bookingRepository.findById(bookingid).isEmpty()) {
			throw new BookingManagementException("Booking does not exist.");
		}else {
			Booking booking=bookingRepository.findById(bookingid).get();
          // Delete the booking by id.
          bookingRepository.deleteById(bookingid);
          // Convert the deleted booking Entity to DTO.
          bookingResponse=modelMapper.map(booking,BookingDTO.class);
          // Return the deleted booking DTO.
			 return bookingResponse;
		}


	}

  // Method to view all bookings.
  @Override
	public List<BookingDTO> viewBookings(){
      // Retrieve all bookings from the repository and convert them to DTOs using stream and collect.
       List<BookingDTO> bookingList=bookingRepository.findAll().stream()
				.map((Booking booking)->modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
       // Check if the list is empty and throw an exception if no bookings are available.
      if(bookingList.size()==0) {
			throw new BookingManagementException("No booking available for given bookingDate.");
		}
		else {
			return bookingList;
		}
	}
  // Method to view a booking by bookingId.
  @Override
	public BookingDTO viewBookingByBookingId(Integer bookingid) {
      // Check if the booking with the given id exists.
      if(!bookingRepository.existsById(bookingid)) {
			throw new BookingManagementException("Booking does not exist for given BookingId.");
		}else {
      // Retrieve the booking by id and convert it to DTO.
      BookingDTO bookingResponse=modelMapper.map(bookingRepository.findById(bookingid).get(),BookingDTO.class);
		return bookingResponse;
		}
	}
  // Method to view bookings by bookingDate.
  @Override
	public List<BookingDTO> viewBookingByDate(LocalDate bookingdate){
      // Retrieve bookings by bookingDate from the repository and convert them to DTOs using stream and collect.
      List<BookingDTO> bookingList=bookingRepository.findByBookingDate(bookingdate).stream()
				.map((Booking booking)->modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no bookings are available for the given bookingDate.
      if(bookingList.size()==0) {
			throw new BookingManagementException("No booking available for given bookingDate.");
		}
		else {
			return bookingList;
		}
	}



  // Method to view bookings by passengerId.
  @Override
	public List<BookingDTO> viewBookingByPassengerId(Integer passengerId){
      // Initialize a list to store bookings with matching passengerId.
      ArrayList<Booking> a=new ArrayList<>(); 
      // Retrieve all bookings from the repository.
      List<Booking> bk1=bookingRepository.findAll();
      // Iterate through bookings and passengers to find matching bookings.
      for(Booking booking:bk1) {
			for (Passenger passenger : booking.getPassengerList()) {
				if (passenger.getPassengerUIN()==(long)passengerId) {
					a.add(booking);
		}}
		}
      // Convert matching bookings to DTOs using stream and collect.
      List<BookingDTO> bookingDto=a.stream().map((Booking booking)->modelMapper.map(booking,BookingDTO.class)).collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no bookings are available for the given passengerId.
      if(bookingDto.size()==0) {
			throw new BookingManagementException("No booking for given passenger Id");
		}else 
		return bookingDto;

	}

  // Method to view bookings by flightId.
  @Override
	public List<BookingDTO> viewBookingsByFlightId(Integer flightid){
      // Initialize a list to store bookings with matching flightId.
      ArrayList<Booking> a=new ArrayList<>(); 
      // Retrieve all bookings from the repository.
      List<Booking> bk1=bookingRepository.findAll();
      // Iterate through bookings to find matching bookings.
      for(Booking booking:bk1 ) {
				if (booking.getFlight().getFlightId() ==flightid) {
					a.add(booking);
				}}
      // Convert matching bookings to DTOs using stream and collect.
      List<BookingDTO> bookingResponse=bk1.stream().map((Booking booking1)->modelMapper.map(booking1,BookingDTO.class)).collect(Collectors.toList());
      // Check if the list is empty and throw an exception if no bookings are available for the given flight.
      if(bookingResponse.size()==0) {
			throw new BookingManagementException("No booking available for given flight.");
		}else {
		return bookingResponse;
		}
	}

  // Method to update a booking by converting DTO to Entity and saving it.
  @Override
	public BookingDTO updateBooking(BookingDTO bookingDto)
  {
      // Initialize bookingDTO to null.
      BookingDTO booking=null;
		// convert DTO to Entity
				Booking bookingRequest=modelMapper.map(bookingDto, Booking.class);
		        // Check if the booking with the given id exists.
              if(bookingRepository.existsById(bookingRequest.getBookingId())) 
              {
					// convert Entity to DTO
					BookingDTO bookingResponse=modelMapper.map(bookingRepository.save(bookingRequest),BookingDTO.class);
				    return bookingResponse;
				}
				else 
				{
		            // Throw an exception if no booking is available for the given id.

					throw new BookingManagementException("No booking available for given flight.") ;
	            }


	}
}