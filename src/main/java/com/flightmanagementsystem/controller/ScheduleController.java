package com.flightmanagementsystem.controller;
 
import java.time.LocalDate;
import java.util.List;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.flightmanagementsystem.dto.ScheduleDTO;
import com.flightmanagementsystem.service.ScheduleService;
 
import jakarta.validation.Valid;
 
//Controller for handling restful requests
@RestController
public class ScheduleController 
{
 
    // Inject the ScheduleService dependency
 
	@Autowired
	private ScheduleService scheduleService;
    // Endpoint to add a new schedule
    @PostMapping("/addschedule")
	public String addScheduleDetails(@Valid @RequestBody ScheduleDTO schedule) 
	{  
        // Call the ScheduleService to add a new schedule
 
	   ScheduleDTO a=scheduleService.addSchedule(schedule);
		return "Successfully added";
	}
    // Endpoint to update an existing schedule
 
	@PutMapping("/updateschedule")
	public String updateSchedule(@Valid @RequestBody ScheduleDTO schedule) 
	{
        // Call the ScheduleService to update an existing schedule
 
		scheduleService.updateSchedule(schedule);
	    return "record updated";
	}
    // Endpoint to view all schedules
 
	@GetMapping("/viewSchedules")
	public List<ScheduleDTO> viewAllSchedules()
	{
        // Call the ScheduleService to retrieve all schedules
 
		return scheduleService.viewSchedules();
	}
    // Endpoint to view schedules by source and destination
 
	@GetMapping("/viewBySourceAndDestinationSchedule/{source}/{destination}")
	public List<ScheduleDTO> getBySourceAndDestination(@PathVariable("source") String source,
			@PathVariable("destination") String destination) 
	{
        // Call the ScheduleService to retrieve schedules by source and destination
 
		return scheduleService.viewBySourceAndDestination(source, destination);
	}
    // Endpoint to view schedules by source, destination, and departure date
 
	@GetMapping("/viewBySourceDestinationAndDepartureDateSchedule/{source}/{destination}/{depdate}")
	public List<ScheduleDTO> getBySourceDestinationAndDepartureDate(@PathVariable("source")String source, @PathVariable("destination")String destination,
			@PathVariable("depdate")LocalDate departureDate) 
	{
        // Call the ScheduleService to retrieve schedules by source, destination, and departure date
 
		return scheduleService.viewBySourceDestinationAndDepartureDate(source,destination,departureDate);
	}
    // Endpoint to view schedules by departure time
 
	@GetMapping("/viewByDepartureTimeSchedule/{dateTime}")
	public List<ScheduleDTO> viewBydateTime(@PathVariable("dateTime") String dateTime)
	{
        // Call the ScheduleService to retrieve schedules by departure time
 
		return scheduleService.viewByDepartureTime(LocalDate.parse(dateTime));
	}
}