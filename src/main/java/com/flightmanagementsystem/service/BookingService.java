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

@Service
public class BookingService implements BookingDao{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public BookingDTO addBooking(BookingDTO bookingDto) {
		// convert DTO to Entity
		Booking bookingRequest=modelMapper.map(bookingDto, Booking.class);
		Booking booking= bookingRepository.save(bookingRequest);
	// convert Entity to DTO 
		BookingDTO bookingResponse=modelMapper.map(booking,BookingDTO.class);
		return bookingResponse;	
		
	}

	@Override
	public BookingDTO cancelBooking(Integer bookingid){
		// TODO Auto-generated method stub
		
		BookingDTO bookingResponse = null;
		if(bookingRepository.findById(bookingid).isEmpty()) {
			throw new BookingManagementException("Booking does not exist.");
		}else {
			Booking booking=bookingRepository.findById(bookingid).get();
			bookingRepository.deleteById(bookingid);
			
			bookingResponse=modelMapper.map(booking,BookingDTO.class);
			
			//it will return deleted bookingid object:
			 return bookingResponse;
		}
		
	

	}

	@Override
	public List<BookingDTO> viewBookings(){
		List<BookingDTO> bookingList=bookingRepository.findAll().stream()
				.map((Booking booking)->modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
		if(bookingList.size()==0) {
			throw new BookingManagementException("No booking available for given bookingDate.");
		}
		else {
			return bookingList;
		}
	
	}
	@Override
	public BookingDTO viewBookingByBookingId(Integer bookingid) {
		if(!bookingRepository.existsById(bookingid)) {
			throw new BookingManagementException("Booking does not exist for given BookingId.");
		}else {
		BookingDTO bookingResponse=modelMapper.map(bookingRepository.findById(bookingid).get(),BookingDTO.class);
		return bookingResponse;
		}
	}
	
	@Override
	public List<BookingDTO> viewBookingByDate(LocalDate bookingdate){
		
		List<BookingDTO> bookingList=bookingRepository.findByBookingDate(bookingdate).stream()
				.map((Booking booking)->modelMapper.map(booking, BookingDTO.class)).collect(Collectors.toList());
	
		if(bookingList.size()==0) {
			throw new BookingManagementException("No booking available for given bookingDate.");
		}
		else {
			return bookingList;
		}
	}
	


	@Override
	public List<BookingDTO> viewBookingByPassengerId(Integer passengerId){
		
		ArrayList<Booking> a=new ArrayList<>(); 
		
		List<Booking> bk1=bookingRepository.findAll();
		
		for(Booking booking:bk1) {
		
			for (Passenger passenger : booking.getPassengerList()) {
				if (passenger.getPassengerUIN()==(long)passengerId) {
					a.add(booking);
		
		}}
			
		}
		List<BookingDTO> bookingDto=a.stream().map((Booking booking)->modelMapper.map(booking,BookingDTO.class)).collect(Collectors.toList());
		
		if(bookingDto.size()==0) {
			throw new BookingManagementException("No booking for given passenger Id");
		}else 
		return bookingDto;

	}

	@Override
	public List<BookingDTO> viewBookingsByFlightId(Integer flightid){
		
ArrayList<Booking> a=new ArrayList<>(); 
		
		List<Booking> bk1=bookingRepository.findAll();
		
		for(Booking booking:bk1 ) {
				if (booking.getFlight().getFlightId() ==flightid) {
					a.add(booking);
				}}
	//	List<Booking> bookingList=bookingRepository.findAll().stream().filter(e -> e.getFlight().getFlightId() == flightid).collect(Collectors.toList());
		
		List<BookingDTO> bookingResponse=bk1.stream().map((Booking booking1)->modelMapper.map(booking1,BookingDTO.class)).collect(Collectors.toList());
		if(bookingResponse.size()==0) {
			throw new BookingManagementException("No booking available for given flight.");
		}else {
		return bookingResponse;
		}
	}

	@Override
	public BookingDTO updateBooking(BookingDTO bookingDto){
		BookingDTO booking=null;
		
		// convert DTO to Entity
				Booking bookingRequest=modelMapper.map(bookingDto, Booking.class);
				
				if(bookingRepository.existsById(bookingRequest.getBookingId())) {
					
					// convert Entity to DTO
					BookingDTO bookingResponse=modelMapper.map(bookingRepository.save(bookingRequest),BookingDTO.class);
				return bookingResponse;
				}
				else {
					throw new BookingManagementException("No booking available for given flight.") ;
			
	}
	
				
			 
				
	}

}
