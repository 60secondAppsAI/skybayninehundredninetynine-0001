package com.skybayninehundredninetynine.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.service.impl.GenericServiceImpl;
import com.skybayninehundredninetynine.dao.ReservationAgentDAO;
import com.skybayninehundredninetynine.domain.ReservationAgent;
import com.skybayninehundredninetynine.dto.ReservationAgentDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentSearchDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentPageDTO;
import com.skybayninehundredninetynine.dto.ReservationAgentConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.ReservationAgentService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class ReservationAgentServiceImpl extends GenericServiceImpl<ReservationAgent, Integer> implements ReservationAgentService {

    private final static Logger logger = LoggerFactory.getLogger(ReservationAgentServiceImpl.class);

	@Autowired
	ReservationAgentDAO reservationAgentDao;

	


	@Override
	public GenericDAO<ReservationAgent, Integer> getDAO() {
		return (GenericDAO<ReservationAgent, Integer>) reservationAgentDao;
	}
	
	public List<ReservationAgent> findAll () {
		List<ReservationAgent> reservationAgents = reservationAgentDao.findAll();
		
		return reservationAgents;	
		
	}

	public ResultDTO addReservationAgent(ReservationAgentDTO reservationAgentDTO, RequestDTO requestDTO) {

		ReservationAgent reservationAgent = new ReservationAgent();

		reservationAgent.setReservationAgentId(reservationAgentDTO.getReservationAgentId());


		reservationAgent.setName(reservationAgentDTO.getName());


		reservationAgent.setEmployeeNumber(reservationAgentDTO.getEmployeeNumber());


		reservationAgent.setContactEmail(reservationAgentDTO.getContactEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		reservationAgent = reservationAgentDao.save(reservationAgent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ReservationAgent> getAllReservationAgents(Pageable pageable) {
		return reservationAgentDao.findAll(pageable);
	}

	public Page<ReservationAgent> getAllReservationAgents(Specification<ReservationAgent> spec, Pageable pageable) {
		return reservationAgentDao.findAll(spec, pageable);
	}

	public ResponseEntity<ReservationAgentPageDTO> getReservationAgents(ReservationAgentSearchDTO reservationAgentSearchDTO) {
	
			Integer reservationAgentId = reservationAgentSearchDTO.getReservationAgentId(); 
 			String name = reservationAgentSearchDTO.getName(); 
 			String employeeNumber = reservationAgentSearchDTO.getEmployeeNumber(); 
 			String contactEmail = reservationAgentSearchDTO.getContactEmail(); 
 			String sortBy = reservationAgentSearchDTO.getSortBy();
			String sortOrder = reservationAgentSearchDTO.getSortOrder();
			String searchQuery = reservationAgentSearchDTO.getSearchQuery();
			Integer page = reservationAgentSearchDTO.getPage();
			Integer size = reservationAgentSearchDTO.getSize();

	        Specification<ReservationAgent> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, reservationAgentId, "reservationAgentId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, employeeNumber, "employeeNumber"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactEmail, "contactEmail"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("employeeNumber")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactEmail")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<ReservationAgent> reservationAgents = this.getAllReservationAgents(spec, pageable);
		
		//System.out.println(String.valueOf(reservationAgents.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(reservationAgents.getTotalPages()));
		
		List<ReservationAgent> reservationAgentsList = reservationAgents.getContent();
		
		ReservationAgentConvertCriteriaDTO convertCriteria = new ReservationAgentConvertCriteriaDTO();
		List<ReservationAgentDTO> reservationAgentDTOs = this.convertReservationAgentsToReservationAgentDTOs(reservationAgentsList,convertCriteria);
		
		ReservationAgentPageDTO reservationAgentPageDTO = new ReservationAgentPageDTO();
		reservationAgentPageDTO.setReservationAgents(reservationAgentDTOs);
		reservationAgentPageDTO.setTotalElements(reservationAgents.getTotalElements());
		return ResponseEntity.ok(reservationAgentPageDTO);
	}

	public List<ReservationAgentDTO> convertReservationAgentsToReservationAgentDTOs(List<ReservationAgent> reservationAgents, ReservationAgentConvertCriteriaDTO convertCriteria) {
		
		List<ReservationAgentDTO> reservationAgentDTOs = new ArrayList<ReservationAgentDTO>();
		
		for (ReservationAgent reservationAgent : reservationAgents) {
			reservationAgentDTOs.add(convertReservationAgentToReservationAgentDTO(reservationAgent,convertCriteria));
		}
		
		return reservationAgentDTOs;

	}
	
	public ReservationAgentDTO convertReservationAgentToReservationAgentDTO(ReservationAgent reservationAgent, ReservationAgentConvertCriteriaDTO convertCriteria) {
		
		ReservationAgentDTO reservationAgentDTO = new ReservationAgentDTO();
		
		reservationAgentDTO.setReservationAgentId(reservationAgent.getReservationAgentId());

	
		reservationAgentDTO.setName(reservationAgent.getName());

	
		reservationAgentDTO.setEmployeeNumber(reservationAgent.getEmployeeNumber());

	
		reservationAgentDTO.setContactEmail(reservationAgent.getContactEmail());

	

		
		return reservationAgentDTO;
	}

	public ResultDTO updateReservationAgent(ReservationAgentDTO reservationAgentDTO, RequestDTO requestDTO) {
		
		ReservationAgent reservationAgent = reservationAgentDao.getById(reservationAgentDTO.getReservationAgentId());

		reservationAgent.setReservationAgentId(ControllerUtils.setValue(reservationAgent.getReservationAgentId(), reservationAgentDTO.getReservationAgentId()));

		reservationAgent.setName(ControllerUtils.setValue(reservationAgent.getName(), reservationAgentDTO.getName()));

		reservationAgent.setEmployeeNumber(ControllerUtils.setValue(reservationAgent.getEmployeeNumber(), reservationAgentDTO.getEmployeeNumber()));

		reservationAgent.setContactEmail(ControllerUtils.setValue(reservationAgent.getContactEmail(), reservationAgentDTO.getContactEmail()));



        reservationAgent = reservationAgentDao.save(reservationAgent);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ReservationAgentDTO getReservationAgentDTOById(Integer reservationAgentId) {
	
		ReservationAgent reservationAgent = reservationAgentDao.getById(reservationAgentId);
			
		
		ReservationAgentConvertCriteriaDTO convertCriteria = new ReservationAgentConvertCriteriaDTO();
		return(this.convertReservationAgentToReservationAgentDTO(reservationAgent,convertCriteria));
	}







}
