package com.skybayninehundredninetynine.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.skybayninehundredninetynine.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.skybayninehundredninetynine.domain.ReservationAgent;
import com.skybayninehundredninetynine.dto.ReservationAgentDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentSearchDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentPageDTO;
import com.skybayninehundredninetynine.service.ReservationAgentService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/reservationAgent")
@RestController
public class ReservationAgentController {

	private final static Logger logger = LoggerFactory.getLogger(ReservationAgentController.class);

	@Autowired
	ReservationAgentService reservationAgentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ReservationAgent> getAll() {

		List<ReservationAgent> reservationAgents = reservationAgentService.findAll();
		
		return reservationAgents;	
	}

	@GetMapping(value = "/{reservationAgentId}")
	@ResponseBody
	public ReservationAgentDTO getReservationAgent(@PathVariable Integer reservationAgentId) {
		
		return (reservationAgentService.getReservationAgentDTOById(reservationAgentId));
	}

 	@RequestMapping(value = "/addReservationAgent", method = RequestMethod.POST)
	public ResponseEntity<?> addReservationAgent(@RequestBody ReservationAgentDTO reservationAgentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationAgentService.addReservationAgent(reservationAgentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/reservationAgents")
	public ResponseEntity<ReservationAgentPageDTO> getReservationAgents(ReservationAgentSearchDTO reservationAgentSearchDTO) {
 
		return reservationAgentService.getReservationAgents(reservationAgentSearchDTO);
	}	

	@RequestMapping(value = "/updateReservationAgent", method = RequestMethod.POST)
	public ResponseEntity<?> updateReservationAgent(@RequestBody ReservationAgentDTO reservationAgentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = reservationAgentService.updateReservationAgent(reservationAgentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
