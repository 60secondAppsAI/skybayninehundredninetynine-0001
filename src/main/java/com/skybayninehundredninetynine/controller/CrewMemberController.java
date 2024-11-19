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

import com.skybayninehundredninetynine.domain.CrewMember;
import com.skybayninehundredninetynine.dto.CrewMemberDTO;
import com.skybayninehundredninetynine.dto.CrewMemberSearchDTO;
import com.skybayninehundredninetynine.dto.CrewMemberPageDTO;
import com.skybayninehundredninetynine.service.CrewMemberService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/crewMember")
@RestController
public class CrewMemberController {

	private final static Logger logger = LoggerFactory.getLogger(CrewMemberController.class);

	@Autowired
	CrewMemberService crewMemberService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<CrewMember> getAll() {

		List<CrewMember> crewMembers = crewMemberService.findAll();
		
		return crewMembers;	
	}

	@GetMapping(value = "/{crewMemberId}")
	@ResponseBody
	public CrewMemberDTO getCrewMember(@PathVariable Integer crewMemberId) {
		
		return (crewMemberService.getCrewMemberDTOById(crewMemberId));
	}

 	@RequestMapping(value = "/addCrewMember", method = RequestMethod.POST)
	public ResponseEntity<?> addCrewMember(@RequestBody CrewMemberDTO crewMemberDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = crewMemberService.addCrewMember(crewMemberDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/crewMembers")
	public ResponseEntity<CrewMemberPageDTO> getCrewMembers(CrewMemberSearchDTO crewMemberSearchDTO) {
 
		return crewMemberService.getCrewMembers(crewMemberSearchDTO);
	}	

	@RequestMapping(value = "/updateCrewMember", method = RequestMethod.POST)
	public ResponseEntity<?> updateCrewMember(@RequestBody CrewMemberDTO crewMemberDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = crewMemberService.updateCrewMember(crewMemberDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
