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

import com.skybayninehundredninetynine.domain.SeatingClass;
import com.skybayninehundredninetynine.dto.SeatingClassDTO;
import com.skybayninehundredninetynine.dto.SeatingClassSearchDTO;
import com.skybayninehundredninetynine.dto.SeatingClassPageDTO;
import com.skybayninehundredninetynine.service.SeatingClassService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/seatingClass")
@RestController
public class SeatingClassController {

	private final static Logger logger = LoggerFactory.getLogger(SeatingClassController.class);

	@Autowired
	SeatingClassService seatingClassService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SeatingClass> getAll() {

		List<SeatingClass> seatingClasss = seatingClassService.findAll();
		
		return seatingClasss;	
	}

	@GetMapping(value = "/{seatingClassId}")
	@ResponseBody
	public SeatingClassDTO getSeatingClass(@PathVariable Integer seatingClassId) {
		
		return (seatingClassService.getSeatingClassDTOById(seatingClassId));
	}

 	@RequestMapping(value = "/addSeatingClass", method = RequestMethod.POST)
	public ResponseEntity<?> addSeatingClass(@RequestBody SeatingClassDTO seatingClassDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatingClassService.addSeatingClass(seatingClassDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/seatingClasss")
	public ResponseEntity<SeatingClassPageDTO> getSeatingClasss(SeatingClassSearchDTO seatingClassSearchDTO) {
 
		return seatingClassService.getSeatingClasss(seatingClassSearchDTO);
	}	

	@RequestMapping(value = "/updateSeatingClass", method = RequestMethod.POST)
	public ResponseEntity<?> updateSeatingClass(@RequestBody SeatingClassDTO seatingClassDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seatingClassService.updateSeatingClass(seatingClassDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
