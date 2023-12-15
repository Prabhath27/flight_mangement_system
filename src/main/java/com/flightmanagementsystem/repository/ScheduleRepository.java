package com.flightmanagementsystem.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightmanagementsystem.entity.Schedule;

@Repository // Indicates that this interface is a Spring Data repository for managing Schedule entities
public interface ScheduleRepository extends JpaRepository <Schedule,Integer> {


	// Finds schedules by departure date
	public List<Schedule>findByDepartureDate(LocalDateTime dateTime);


}
