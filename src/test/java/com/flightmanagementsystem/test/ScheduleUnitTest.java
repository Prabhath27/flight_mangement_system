package com.flightmanagementsystem.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.flightmanagementsystem.dao.ScheduleDao;
import com.flightmanagementsystem.dto.ScheduleDTO;
import com.flightmanagementsystem.entity.Schedule;
import com.flightmanagementsystem.repository.ScheduleRepository;

@SpringBootTest
public class ScheduleUnitTest {

    @Autowired
    private ScheduleDao scheduleService;

    @MockBean
    private ScheduleRepository scheduleRepository;

    @MockBean
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        // Mocking behavior for addSchedule method
        when(modelMapper.map(any(ScheduleDTO.class), eq(Schedule.class))).thenReturn(new Schedule());
        when(modelMapper.map(any(Schedule.class), eq(ScheduleDTO.class))).thenReturn(new ScheduleDTO());

        Schedule schedule = new Schedule();
        schedule.setScheduleId(1);
        when(scheduleRepository.save(any(Schedule.class))).thenReturn(schedule);

        // Mocking behavior for viewSchedules method
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<Schedule>());

        // Mocking behavior for viewBySourceAndDestination method
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<>());

        // Mocking behavior for viewBySourceDestinationAndDepartureDate method
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<>());

        // Mocking behavior for viewByDepartureTime method
        when(scheduleRepository.findAll()).thenReturn(new ArrayList<>());
    }

    @Test
    public void testAddSchedule() {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        ScheduleDTO result = scheduleService.addSchedule(scheduleDTO);
        assertNotNull(result);
    }

    @Test
    public void testUpdateSchedule() {
        ScheduleDTO scheduleDTO = new ScheduleDTO();
        ScheduleDTO result = scheduleService.updateSchedule(scheduleDTO);
        assertNotNull(result);
    }

    @Test
    public void testViewSchedules() {
        List<ScheduleDTO> result = scheduleService.viewSchedules();
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testViewBySourceAndDestination() {
        List<ScheduleDTO> result = scheduleService.viewBySourceAndDestination("source", "destination");
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testViewBySourceDestinationAndDepartureDate() {
        List<ScheduleDTO> result = scheduleService.viewBySourceDestinationAndDepartureDate("source", "destination", LocalDate.now());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testViewByDepartureTime() {
        List<ScheduleDTO> result = scheduleService.viewByDepartureTime(LocalDate.now());
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
