package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.Airplane;
import com.skybayninehundredninetynine.dto.AirplaneDTO;
import com.skybayninehundredninetynine.dto.AirplaneSearchDTO;
import com.skybayninehundredninetynine.dto.AirplanePageDTO;
import com.skybayninehundredninetynine.dto.AirplaneConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AirplaneService extends GenericService<Airplane, Integer> {

	List<Airplane> findAll();

	ResultDTO addAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

	ResultDTO updateAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO);

    Page<Airplane> getAllAirplanes(Pageable pageable);

    Page<Airplane> getAllAirplanes(Specification<Airplane> spec, Pageable pageable);

	ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO);
	
	List<AirplaneDTO> convertAirplanesToAirplaneDTOs(List<Airplane> airplanes, AirplaneConvertCriteriaDTO convertCriteria);

	AirplaneDTO getAirplaneDTOById(Integer airplaneId);







}





