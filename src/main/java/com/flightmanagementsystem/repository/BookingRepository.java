package com.flightmanagementsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository <Booking, Integer>{


	public List<Booking> findByBookingDate(LocalDate bookingdate);
}
