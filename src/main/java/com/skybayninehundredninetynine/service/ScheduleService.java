package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.Schedule;
import com.skybayninehundredninetynine.dto.ScheduleDTO;
import com.skybayninehundredninetynine.dto.ScheduleSearchDTO;
import com.skybayninehundredninetynine.dto.SchedulePageDTO;
import com.skybayninehundredninetynine.dto.ScheduleConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ScheduleService extends GenericService<Schedule, Integer> {

	List<Schedule> findAll();

	ResultDTO addSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO);

	ResultDTO updateSchedule(ScheduleDTO scheduleDTO, RequestDTO requestDTO);

    Page<Schedule> getAllSchedules(Pageable pageable);

    Page<Schedule> getAllSchedules(Specification<Schedule> spec, Pageable pageable);

	ResponseEntity<SchedulePageDTO> getSchedules(ScheduleSearchDTO scheduleSearchDTO);
	
	List<ScheduleDTO> convertSchedulesToScheduleDTOs(List<Schedule> schedules, ScheduleConvertCriteriaDTO convertCriteria);

	ScheduleDTO getScheduleDTOById(Integer scheduleId);







}





