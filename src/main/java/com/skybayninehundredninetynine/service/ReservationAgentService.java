package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.ReservationAgent;
import com.skybayninehundredninetynine.dto.ReservationAgentDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentSearchDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentPageDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ReservationAgentService extends GenericService<ReservationAgent, Integer> {

	List<ReservationAgent> findAll();

	ResultDTO addReservationAgent(ReservationAgentDTO reservationAgentDTO, RequestDTO requestDTO);

	ResultDTO updateReservationAgent(ReservationAgentDTO reservationAgentDTO, RequestDTO requestDTO);

    Page<ReservationAgent> getAllReservationAgents(Pageable pageable);

    Page<ReservationAgent> getAllReservationAgents(Specification<ReservationAgent> spec, Pageable pageable);

	ResponseEntity<ReservationAgentPageDTO> getReservationAgents(ReservationAgentSearchDTO reservationAgentSearchDTO);
	
	List<ReservationAgentDTO> convertReservationAgentsToReservationAgentDTOs(List<ReservationAgent> reservationAgents, ReservationAgentConvertCriteriaDTO convertCriteria);

	ReservationAgentDTO getReservationAgentDTOById(Integer reservationAgentId);







}





