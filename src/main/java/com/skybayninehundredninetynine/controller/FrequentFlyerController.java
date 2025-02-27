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

import com.skybayninehundredninetynine.domain.FrequentFlyer;
import com.skybayninehundredninetynine.dto.FrequentFlyerDTO;
import com.skybayninehundredninetynine.dto.FrequentFlyerSearchDTO;
import com.skybayninehundredninetynine.dto.FrequentFlyerPageDTO;
import com.skybayninehundredninetynine.service.FrequentFlyerService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/frequentFlyer")
@RestController
public class FrequentFlyerController {

	private final static Logger logger = LoggerFactory.getLogger(FrequentFlyerController.class);

	@Autowired
	FrequentFlyerService frequentFlyerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<FrequentFlyer> getAll() {

		List<FrequentFlyer> frequentFlyers = frequentFlyerService.findAll();
		
		return frequentFlyers;	
	}

	@GetMapping(value = "/{frequentFlyerId}")
	@ResponseBody
	public FrequentFlyerDTO getFrequentFlyer(@PathVariable Integer frequentFlyerId) {
		
		return (frequentFlyerService.getFrequentFlyerDTOById(frequentFlyerId));
	}

 	@RequestMapping(value = "/addFrequentFlyer", method = RequestMethod.POST)
	public ResponseEntity<?> addFrequentFlyer(@RequestBody FrequentFlyerDTO frequentFlyerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = frequentFlyerService.addFrequentFlyer(frequentFlyerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/frequentFlyers")
	public ResponseEntity<FrequentFlyerPageDTO> getFrequentFlyers(FrequentFlyerSearchDTO frequentFlyerSearchDTO) {
 
		return frequentFlyerService.getFrequentFlyers(frequentFlyerSearchDTO);
	}	

	@RequestMapping(value = "/updateFrequentFlyer", method = RequestMethod.POST)
	public ResponseEntity<?> updateFrequentFlyer(@RequestBody FrequentFlyerDTO frequentFlyerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = frequentFlyerService.updateFrequentFlyer(frequentFlyerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
