package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.Luggage;
import com.skybayninehundredninetynine.dto.LuggageDTO;
import com.skybayninehundredninetynine.dto.LuggageSearchDTO;
import com.skybayninehundredninetynine.dto.LuggagePageDTO;
import com.skybayninehundredninetynine.dto.LuggageConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface LuggageService extends GenericService<Luggage, Integer> {

	List<Luggage> findAll();

	ResultDTO addLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO);

	ResultDTO updateLuggage(LuggageDTO luggageDTO, RequestDTO requestDTO);

    Page<Luggage> getAllLuggages(Pageable pageable);

    Page<Luggage> getAllLuggages(Specification<Luggage> spec, Pageable pageable);

	ResponseEntity<LuggagePageDTO> getLuggages(LuggageSearchDTO luggageSearchDTO);
	
	List<LuggageDTO> convertLuggagesToLuggageDTOs(List<Luggage> luggages, LuggageConvertCriteriaDTO convertCriteria);

	LuggageDTO getLuggageDTOById(Integer luggageId);







}





