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
import com.skybayninehundredninetynine.dao.SeatingClassDAO;
import com.skybayninehundredninetynine.domain.SeatingClass;
import com.skybayninehundredninetynine.dto.SeatingClassDTO;
import com.skybayninehundredninetynine.dto.SeatingClassSearchDTO;
import com.skybayninehundredninetynine.dto.SeatingClassPageDTO;
import com.skybayninehundredninetynine.dto.SeatingClassConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.SeatingClassService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class SeatingClassServiceImpl extends GenericServiceImpl<SeatingClass, Integer> implements SeatingClassService {

    private final static Logger logger = LoggerFactory.getLogger(SeatingClassServiceImpl.class);

	@Autowired
	SeatingClassDAO seatingClassDao;

	


	@Override
	public GenericDAO<SeatingClass, Integer> getDAO() {
		return (GenericDAO<SeatingClass, Integer>) seatingClassDao;
	}
	
	public List<SeatingClass> findAll () {
		List<SeatingClass> seatingClasss = seatingClassDao.findAll();
		
		return seatingClasss;	
		
	}

	public ResultDTO addSeatingClass(SeatingClassDTO seatingClassDTO, RequestDTO requestDTO) {

		SeatingClass seatingClass = new SeatingClass();

		seatingClass.setSeatingClassId(seatingClassDTO.getSeatingClassId());


		seatingClass.setClassName(seatingClassDTO.getClassName());


		seatingClass.setDescription(seatingClassDTO.getDescription());


		seatingClass.setPriceMultiplier(seatingClassDTO.getPriceMultiplier());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		seatingClass = seatingClassDao.save(seatingClass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SeatingClass> getAllSeatingClasss(Pageable pageable) {
		return seatingClassDao.findAll(pageable);
	}

	public Page<SeatingClass> getAllSeatingClasss(Specification<SeatingClass> spec, Pageable pageable) {
		return seatingClassDao.findAll(spec, pageable);
	}

	public ResponseEntity<SeatingClassPageDTO> getSeatingClasss(SeatingClassSearchDTO seatingClassSearchDTO) {
	
			Integer seatingClassId = seatingClassSearchDTO.getSeatingClassId(); 
 			String className = seatingClassSearchDTO.getClassName(); 
 			String description = seatingClassSearchDTO.getDescription(); 
  			String sortBy = seatingClassSearchDTO.getSortBy();
			String sortOrder = seatingClassSearchDTO.getSortOrder();
			String searchQuery = seatingClassSearchDTO.getSearchQuery();
			Integer page = seatingClassSearchDTO.getPage();
			Integer size = seatingClassSearchDTO.getSize();

	        Specification<SeatingClass> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, seatingClassId, "seatingClassId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, className, "className"); 
			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("className")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SeatingClass> seatingClasss = this.getAllSeatingClasss(spec, pageable);
		
		//System.out.println(String.valueOf(seatingClasss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(seatingClasss.getTotalPages()));
		
		List<SeatingClass> seatingClasssList = seatingClasss.getContent();
		
		SeatingClassConvertCriteriaDTO convertCriteria = new SeatingClassConvertCriteriaDTO();
		List<SeatingClassDTO> seatingClassDTOs = this.convertSeatingClasssToSeatingClassDTOs(seatingClasssList,convertCriteria);
		
		SeatingClassPageDTO seatingClassPageDTO = new SeatingClassPageDTO();
		seatingClassPageDTO.setSeatingClasss(seatingClassDTOs);
		seatingClassPageDTO.setTotalElements(seatingClasss.getTotalElements());
		return ResponseEntity.ok(seatingClassPageDTO);
	}

	public List<SeatingClassDTO> convertSeatingClasssToSeatingClassDTOs(List<SeatingClass> seatingClasss, SeatingClassConvertCriteriaDTO convertCriteria) {
		
		List<SeatingClassDTO> seatingClassDTOs = new ArrayList<SeatingClassDTO>();
		
		for (SeatingClass seatingClass : seatingClasss) {
			seatingClassDTOs.add(convertSeatingClassToSeatingClassDTO(seatingClass,convertCriteria));
		}
		
		return seatingClassDTOs;

	}
	
	public SeatingClassDTO convertSeatingClassToSeatingClassDTO(SeatingClass seatingClass, SeatingClassConvertCriteriaDTO convertCriteria) {
		
		SeatingClassDTO seatingClassDTO = new SeatingClassDTO();
		
		seatingClassDTO.setSeatingClassId(seatingClass.getSeatingClassId());

	
		seatingClassDTO.setClassName(seatingClass.getClassName());

	
		seatingClassDTO.setDescription(seatingClass.getDescription());

	
		seatingClassDTO.setPriceMultiplier(seatingClass.getPriceMultiplier());

	

		
		return seatingClassDTO;
	}

	public ResultDTO updateSeatingClass(SeatingClassDTO seatingClassDTO, RequestDTO requestDTO) {
		
		SeatingClass seatingClass = seatingClassDao.getById(seatingClassDTO.getSeatingClassId());

		seatingClass.setSeatingClassId(ControllerUtils.setValue(seatingClass.getSeatingClassId(), seatingClassDTO.getSeatingClassId()));

		seatingClass.setClassName(ControllerUtils.setValue(seatingClass.getClassName(), seatingClassDTO.getClassName()));

		seatingClass.setDescription(ControllerUtils.setValue(seatingClass.getDescription(), seatingClassDTO.getDescription()));

		seatingClass.setPriceMultiplier(ControllerUtils.setValue(seatingClass.getPriceMultiplier(), seatingClassDTO.getPriceMultiplier()));



        seatingClass = seatingClassDao.save(seatingClass);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SeatingClassDTO getSeatingClassDTOById(Integer seatingClassId) {
	
		SeatingClass seatingClass = seatingClassDao.getById(seatingClassId);
			
		
		SeatingClassConvertCriteriaDTO convertCriteria = new SeatingClassConvertCriteriaDTO();
		return(this.convertSeatingClassToSeatingClassDTO(seatingClass,convertCriteria));
	}







}
