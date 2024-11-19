package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.SeatingClass;
import com.skybayninehundredninetynine.dto.SeatingClassDTO;
import com.skybayninehundredninetynine.dto.SeatingClassSearchDTO;
import com.skybayninehundredninetynine.dto.SeatingClassPageDTO;
import com.skybayninehundredninetynine.dto.SeatingClassConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeatingClassService extends GenericService<SeatingClass, Integer> {

	List<SeatingClass> findAll();

	ResultDTO addSeatingClass(SeatingClassDTO seatingClassDTO, RequestDTO requestDTO);

	ResultDTO updateSeatingClass(SeatingClassDTO seatingClassDTO, RequestDTO requestDTO);

    Page<SeatingClass> getAllSeatingClasss(Pageable pageable);

    Page<SeatingClass> getAllSeatingClasss(Specification<SeatingClass> spec, Pageable pageable);

	ResponseEntity<SeatingClassPageDTO> getSeatingClasss(SeatingClassSearchDTO seatingClassSearchDTO);
	
	List<SeatingClassDTO> convertSeatingClasssToSeatingClassDTOs(List<SeatingClass> seatingClasss, SeatingClassConvertCriteriaDTO convertCriteria);

	SeatingClassDTO getSeatingClassDTOById(Integer seatingClassId);







}





