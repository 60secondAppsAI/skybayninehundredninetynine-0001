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
import com.skybayninehundredninetynine.dao.AirplaneDAO;
import com.skybayninehundredninetynine.domain.Airplane;
import com.skybayninehundredninetynine.dto.AirplaneDTO;
import com.skybayninehundredninetynine.dto.AirplaneSearchDTO;
import com.skybayninehundredninetynine.dto.AirplanePageDTO;
import com.skybayninehundredninetynine.dto.AirplaneConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.AirplaneService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class AirplaneServiceImpl extends GenericServiceImpl<Airplane, Integer> implements AirplaneService {

    private final static Logger logger = LoggerFactory.getLogger(AirplaneServiceImpl.class);

	@Autowired
	AirplaneDAO airplaneDao;

	


	@Override
	public GenericDAO<Airplane, Integer> getDAO() {
		return (GenericDAO<Airplane, Integer>) airplaneDao;
	}
	
	public List<Airplane> findAll () {
		List<Airplane> airplanes = airplaneDao.findAll();
		
		return airplanes;	
		
	}

	public ResultDTO addAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO) {

		Airplane airplane = new Airplane();

		airplane.setAirplaneId(airplaneDTO.getAirplaneId());


		airplane.setModel(airplaneDTO.getModel());


		airplane.setManufacturer(airplaneDTO.getManufacturer());


		airplane.setSeatCapacity(airplaneDTO.getSeatCapacity());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		airplane = airplaneDao.save(airplane);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Airplane> getAllAirplanes(Pageable pageable) {
		return airplaneDao.findAll(pageable);
	}

	public Page<Airplane> getAllAirplanes(Specification<Airplane> spec, Pageable pageable) {
		return airplaneDao.findAll(spec, pageable);
	}

	public ResponseEntity<AirplanePageDTO> getAirplanes(AirplaneSearchDTO airplaneSearchDTO) {
	
			Integer airplaneId = airplaneSearchDTO.getAirplaneId(); 
 			String model = airplaneSearchDTO.getModel(); 
 			String manufacturer = airplaneSearchDTO.getManufacturer(); 
  			String sortBy = airplaneSearchDTO.getSortBy();
			String sortOrder = airplaneSearchDTO.getSortOrder();
			String searchQuery = airplaneSearchDTO.getSearchQuery();
			Integer page = airplaneSearchDTO.getPage();
			Integer size = airplaneSearchDTO.getSize();

	        Specification<Airplane> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, airplaneId, "airplaneId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, model, "model"); 
			
			spec = ControllerUtils.andIfNecessary(spec, manufacturer, "manufacturer"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("model")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("manufacturer")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Airplane> airplanes = this.getAllAirplanes(spec, pageable);
		
		//System.out.println(String.valueOf(airplanes.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(airplanes.getTotalPages()));
		
		List<Airplane> airplanesList = airplanes.getContent();
		
		AirplaneConvertCriteriaDTO convertCriteria = new AirplaneConvertCriteriaDTO();
		List<AirplaneDTO> airplaneDTOs = this.convertAirplanesToAirplaneDTOs(airplanesList,convertCriteria);
		
		AirplanePageDTO airplanePageDTO = new AirplanePageDTO();
		airplanePageDTO.setAirplanes(airplaneDTOs);
		airplanePageDTO.setTotalElements(airplanes.getTotalElements());
		return ResponseEntity.ok(airplanePageDTO);
	}

	public List<AirplaneDTO> convertAirplanesToAirplaneDTOs(List<Airplane> airplanes, AirplaneConvertCriteriaDTO convertCriteria) {
		
		List<AirplaneDTO> airplaneDTOs = new ArrayList<AirplaneDTO>();
		
		for (Airplane airplane : airplanes) {
			airplaneDTOs.add(convertAirplaneToAirplaneDTO(airplane,convertCriteria));
		}
		
		return airplaneDTOs;

	}
	
	public AirplaneDTO convertAirplaneToAirplaneDTO(Airplane airplane, AirplaneConvertCriteriaDTO convertCriteria) {
		
		AirplaneDTO airplaneDTO = new AirplaneDTO();
		
		airplaneDTO.setAirplaneId(airplane.getAirplaneId());

	
		airplaneDTO.setModel(airplane.getModel());

	
		airplaneDTO.setManufacturer(airplane.getManufacturer());

	
		airplaneDTO.setSeatCapacity(airplane.getSeatCapacity());

	

		
		return airplaneDTO;
	}

	public ResultDTO updateAirplane(AirplaneDTO airplaneDTO, RequestDTO requestDTO) {
		
		Airplane airplane = airplaneDao.getById(airplaneDTO.getAirplaneId());

		airplane.setAirplaneId(ControllerUtils.setValue(airplane.getAirplaneId(), airplaneDTO.getAirplaneId()));

		airplane.setModel(ControllerUtils.setValue(airplane.getModel(), airplaneDTO.getModel()));

		airplane.setManufacturer(ControllerUtils.setValue(airplane.getManufacturer(), airplaneDTO.getManufacturer()));

		airplane.setSeatCapacity(ControllerUtils.setValue(airplane.getSeatCapacity(), airplaneDTO.getSeatCapacity()));



        airplane = airplaneDao.save(airplane);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AirplaneDTO getAirplaneDTOById(Integer airplaneId) {
	
		Airplane airplane = airplaneDao.getById(airplaneId);
			
		
		AirplaneConvertCriteriaDTO convertCriteria = new AirplaneConvertCriteriaDTO();
		return(this.convertAirplaneToAirplaneDTO(airplane,convertCriteria));
	}







}
