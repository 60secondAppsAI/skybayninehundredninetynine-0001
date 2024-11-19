package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.FrequentFlyer;
import com.skybayninehundredninetynine.dto.FrequentFlyerDTO;
import com.skybayninehundredninetynine.dto.FrequentFlyerSearchDTO;
import com.skybayninehundredninetynine.dto.FrequentFlyerPageDTO;
import com.skybayninehundredninetynine.dto.FrequentFlyerConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface FrequentFlyerService extends GenericService<FrequentFlyer, Integer> {

	List<FrequentFlyer> findAll();

	ResultDTO addFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

	ResultDTO updateFrequentFlyer(FrequentFlyerDTO frequentFlyerDTO, RequestDTO requestDTO);

    Page<FrequentFlyer> getAllFrequentFlyers(Pageable pageable);

    Page<FrequentFlyer> getAllFrequentFlyers(Specification<FrequentFlyer> spec, Pageable pageable);

	ResponseEntity<FrequentFlyerPageDTO> getFrequentFlyers(FrequentFlyerSearchDTO frequentFlyerSearchDTO);
	
	List<FrequentFlyerDTO> convertFrequentFlyersToFrequentFlyerDTOs(List<FrequentFlyer> frequentFlyers, FrequentFlyerConvertCriteriaDTO convertCriteria);

	FrequentFlyerDTO getFrequentFlyerDTOById(Integer frequentFlyerId);







}





