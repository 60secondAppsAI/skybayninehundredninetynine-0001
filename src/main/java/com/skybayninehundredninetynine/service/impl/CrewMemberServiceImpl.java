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
import com.skybayninehundredninetynine.dao.CrewMemberDAO;
import com.skybayninehundredninetynine.domain.CrewMember;
import com.skybayninehundredninetynine.dto.CrewMemberDTO;
import com.skybayninehundredninetynine.dto.CrewMemberSearchDTO;
import com.skybayninehundredninetynine.dto.CrewMemberPageDTO;
import com.skybayninehundredninetynine.dto.CrewMemberConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.CrewMemberService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class CrewMemberServiceImpl extends GenericServiceImpl<CrewMember, Integer> implements CrewMemberService {

    private final static Logger logger = LoggerFactory.getLogger(CrewMemberServiceImpl.class);

	@Autowired
	CrewMemberDAO crewMemberDao;

	


	@Override
	public GenericDAO<CrewMember, Integer> getDAO() {
		return (GenericDAO<CrewMember, Integer>) crewMemberDao;
	}
	
	public List<CrewMember> findAll () {
		List<CrewMember> crewMembers = crewMemberDao.findAll();
		
		return crewMembers;	
		
	}

	public ResultDTO addCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO) {

		CrewMember crewMember = new CrewMember();

		crewMember.setCrewMemberId(crewMemberDTO.getCrewMemberId());


		crewMember.setName(crewMemberDTO.getName());


		crewMember.setPosition(crewMemberDTO.getPosition());


		crewMember.setContactNumber(crewMemberDTO.getContactNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		crewMember = crewMemberDao.save(crewMember);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<CrewMember> getAllCrewMembers(Pageable pageable) {
		return crewMemberDao.findAll(pageable);
	}

	public Page<CrewMember> getAllCrewMembers(Specification<CrewMember> spec, Pageable pageable) {
		return crewMemberDao.findAll(spec, pageable);
	}

	public ResponseEntity<CrewMemberPageDTO> getCrewMembers(CrewMemberSearchDTO crewMemberSearchDTO) {
	
			Integer crewMemberId = crewMemberSearchDTO.getCrewMemberId(); 
 			String name = crewMemberSearchDTO.getName(); 
 			String position = crewMemberSearchDTO.getPosition(); 
 			String contactNumber = crewMemberSearchDTO.getContactNumber(); 
 			String sortBy = crewMemberSearchDTO.getSortBy();
			String sortOrder = crewMemberSearchDTO.getSortOrder();
			String searchQuery = crewMemberSearchDTO.getSearchQuery();
			Integer page = crewMemberSearchDTO.getPage();
			Integer size = crewMemberSearchDTO.getSize();

	        Specification<CrewMember> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, crewMemberId, "crewMemberId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, position, "position"); 
			
			spec = ControllerUtils.andIfNecessary(spec, contactNumber, "contactNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("position")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("contactNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<CrewMember> crewMembers = this.getAllCrewMembers(spec, pageable);
		
		//System.out.println(String.valueOf(crewMembers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(crewMembers.getTotalPages()));
		
		List<CrewMember> crewMembersList = crewMembers.getContent();
		
		CrewMemberConvertCriteriaDTO convertCriteria = new CrewMemberConvertCriteriaDTO();
		List<CrewMemberDTO> crewMemberDTOs = this.convertCrewMembersToCrewMemberDTOs(crewMembersList,convertCriteria);
		
		CrewMemberPageDTO crewMemberPageDTO = new CrewMemberPageDTO();
		crewMemberPageDTO.setCrewMembers(crewMemberDTOs);
		crewMemberPageDTO.setTotalElements(crewMembers.getTotalElements());
		return ResponseEntity.ok(crewMemberPageDTO);
	}

	public List<CrewMemberDTO> convertCrewMembersToCrewMemberDTOs(List<CrewMember> crewMembers, CrewMemberConvertCriteriaDTO convertCriteria) {
		
		List<CrewMemberDTO> crewMemberDTOs = new ArrayList<CrewMemberDTO>();
		
		for (CrewMember crewMember : crewMembers) {
			crewMemberDTOs.add(convertCrewMemberToCrewMemberDTO(crewMember,convertCriteria));
		}
		
		return crewMemberDTOs;

	}
	
	public CrewMemberDTO convertCrewMemberToCrewMemberDTO(CrewMember crewMember, CrewMemberConvertCriteriaDTO convertCriteria) {
		
		CrewMemberDTO crewMemberDTO = new CrewMemberDTO();
		
		crewMemberDTO.setCrewMemberId(crewMember.getCrewMemberId());

	
		crewMemberDTO.setName(crewMember.getName());

	
		crewMemberDTO.setPosition(crewMember.getPosition());

	
		crewMemberDTO.setContactNumber(crewMember.getContactNumber());

	

		
		return crewMemberDTO;
	}

	public ResultDTO updateCrewMember(CrewMemberDTO crewMemberDTO, RequestDTO requestDTO) {
		
		CrewMember crewMember = crewMemberDao.getById(crewMemberDTO.getCrewMemberId());

		crewMember.setCrewMemberId(ControllerUtils.setValue(crewMember.getCrewMemberId(), crewMemberDTO.getCrewMemberId()));

		crewMember.setName(ControllerUtils.setValue(crewMember.getName(), crewMemberDTO.getName()));

		crewMember.setPosition(ControllerUtils.setValue(crewMember.getPosition(), crewMemberDTO.getPosition()));

		crewMember.setContactNumber(ControllerUtils.setValue(crewMember.getContactNumber(), crewMemberDTO.getContactNumber()));



        crewMember = crewMemberDao.save(crewMember);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CrewMemberDTO getCrewMemberDTOById(Integer crewMemberId) {
	
		CrewMember crewMember = crewMemberDao.getById(crewMemberId);
			
		
		CrewMemberConvertCriteriaDTO convertCriteria = new CrewMemberConvertCriteriaDTO();
		return(this.convertCrewMemberToCrewMemberDTO(crewMember,convertCriteria));
	}







}
