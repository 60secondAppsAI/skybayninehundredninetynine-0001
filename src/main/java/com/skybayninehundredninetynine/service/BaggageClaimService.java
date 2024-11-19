package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.BaggageClaim;
import com.skybayninehundredninetynine.dto.BaggageClaimDTO;
import com.skybayninehundredninetynine.dto.BaggageClaimSearchDTO;
import com.skybayninehundredninetynine.dto.BaggageClaimPageDTO;
import com.skybayninehundredninetynine.dto.BaggageClaimConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface BaggageClaimService extends GenericService<BaggageClaim, Integer> {

	List<BaggageClaim> findAll();

	ResultDTO addBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

	ResultDTO updateBaggageClaim(BaggageClaimDTO baggageClaimDTO, RequestDTO requestDTO);

    Page<BaggageClaim> getAllBaggageClaims(Pageable pageable);

    Page<BaggageClaim> getAllBaggageClaims(Specification<BaggageClaim> spec, Pageable pageable);

	ResponseEntity<BaggageClaimPageDTO> getBaggageClaims(BaggageClaimSearchDTO baggageClaimSearchDTO);
	
	List<BaggageClaimDTO> convertBaggageClaimsToBaggageClaimDTOs(List<BaggageClaim> baggageClaims, BaggageClaimConvertCriteriaDTO convertCriteria);

	BaggageClaimDTO getBaggageClaimDTOById(Integer baggageClaimId);







}





