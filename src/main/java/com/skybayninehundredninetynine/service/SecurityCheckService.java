package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.SecurityCheck;
import com.skybayninehundredninetynine.dto.SecurityCheckDTO;
import com.skybayninehundredninetynine.dto.SecurityCheckSearchDTO;
import com.skybayninehundredninetynine.dto.SecurityCheckPageDTO;
import com.skybayninehundredninetynine.dto.SecurityCheckConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SecurityCheckService extends GenericService<SecurityCheck, Integer> {

	List<SecurityCheck> findAll();

	ResultDTO addSecurityCheck(SecurityCheckDTO securityCheckDTO, RequestDTO requestDTO);

	ResultDTO updateSecurityCheck(SecurityCheckDTO securityCheckDTO, RequestDTO requestDTO);

    Page<SecurityCheck> getAllSecurityChecks(Pageable pageable);

    Page<SecurityCheck> getAllSecurityChecks(Specification<SecurityCheck> spec, Pageable pageable);

	ResponseEntity<SecurityCheckPageDTO> getSecurityChecks(SecurityCheckSearchDTO securityCheckSearchDTO);
	
	List<SecurityCheckDTO> convertSecurityChecksToSecurityCheckDTOs(List<SecurityCheck> securityChecks, SecurityCheckConvertCriteriaDTO convertCriteria);

	SecurityCheckDTO getSecurityCheckDTOById(Integer securityCheckId);







}





