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
import com.skybayninehundredninetynine.dao.MaintenanceDAO;
import com.skybayninehundredninetynine.domain.Maintenance;
import com.skybayninehundredninetynine.dto.MaintenanceDTO;
import com.skybayninehundredninetynine.dto.MaintenanceSearchDTO;
import com.skybayninehundredninetynine.dto.MaintenancePageDTO;
import com.skybayninehundredninetynine.dto.MaintenanceConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.MaintenanceService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class MaintenanceServiceImpl extends GenericServiceImpl<Maintenance, Integer> implements MaintenanceService {

    private final static Logger logger = LoggerFactory.getLogger(MaintenanceServiceImpl.class);

	@Autowired
	MaintenanceDAO maintenanceDao;

	


	@Override
	public GenericDAO<Maintenance, Integer> getDAO() {
		return (GenericDAO<Maintenance, Integer>) maintenanceDao;
	}
	
	public List<Maintenance> findAll () {
		List<Maintenance> maintenances = maintenanceDao.findAll();
		
		return maintenances;	
		
	}

	public ResultDTO addMaintenance(MaintenanceDTO maintenanceDTO, RequestDTO requestDTO) {

		Maintenance maintenance = new Maintenance();

		maintenance.setMaintenanceId(maintenanceDTO.getMaintenanceId());


		maintenance.setServiceDate(maintenanceDTO.getServiceDate());


		maintenance.setDescription(maintenanceDTO.getDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		maintenance = maintenanceDao.save(maintenance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Maintenance> getAllMaintenances(Pageable pageable) {
		return maintenanceDao.findAll(pageable);
	}

	public Page<Maintenance> getAllMaintenances(Specification<Maintenance> spec, Pageable pageable) {
		return maintenanceDao.findAll(spec, pageable);
	}

	public ResponseEntity<MaintenancePageDTO> getMaintenances(MaintenanceSearchDTO maintenanceSearchDTO) {
	
			Integer maintenanceId = maintenanceSearchDTO.getMaintenanceId(); 
   			String description = maintenanceSearchDTO.getDescription(); 
 			String sortBy = maintenanceSearchDTO.getSortBy();
			String sortOrder = maintenanceSearchDTO.getSortOrder();
			String searchQuery = maintenanceSearchDTO.getSearchQuery();
			Integer page = maintenanceSearchDTO.getPage();
			Integer size = maintenanceSearchDTO.getSize();

	        Specification<Maintenance> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, maintenanceId, "maintenanceId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, description, "description"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("description")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Maintenance> maintenances = this.getAllMaintenances(spec, pageable);
		
		//System.out.println(String.valueOf(maintenances.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(maintenances.getTotalPages()));
		
		List<Maintenance> maintenancesList = maintenances.getContent();
		
		MaintenanceConvertCriteriaDTO convertCriteria = new MaintenanceConvertCriteriaDTO();
		List<MaintenanceDTO> maintenanceDTOs = this.convertMaintenancesToMaintenanceDTOs(maintenancesList,convertCriteria);
		
		MaintenancePageDTO maintenancePageDTO = new MaintenancePageDTO();
		maintenancePageDTO.setMaintenances(maintenanceDTOs);
		maintenancePageDTO.setTotalElements(maintenances.getTotalElements());
		return ResponseEntity.ok(maintenancePageDTO);
	}

	public List<MaintenanceDTO> convertMaintenancesToMaintenanceDTOs(List<Maintenance> maintenances, MaintenanceConvertCriteriaDTO convertCriteria) {
		
		List<MaintenanceDTO> maintenanceDTOs = new ArrayList<MaintenanceDTO>();
		
		for (Maintenance maintenance : maintenances) {
			maintenanceDTOs.add(convertMaintenanceToMaintenanceDTO(maintenance,convertCriteria));
		}
		
		return maintenanceDTOs;

	}
	
	public MaintenanceDTO convertMaintenanceToMaintenanceDTO(Maintenance maintenance, MaintenanceConvertCriteriaDTO convertCriteria) {
		
		MaintenanceDTO maintenanceDTO = new MaintenanceDTO();
		
		maintenanceDTO.setMaintenanceId(maintenance.getMaintenanceId());

	
		maintenanceDTO.setServiceDate(maintenance.getServiceDate());

	
		maintenanceDTO.setDescription(maintenance.getDescription());

	

		
		return maintenanceDTO;
	}

	public ResultDTO updateMaintenance(MaintenanceDTO maintenanceDTO, RequestDTO requestDTO) {
		
		Maintenance maintenance = maintenanceDao.getById(maintenanceDTO.getMaintenanceId());

		maintenance.setMaintenanceId(ControllerUtils.setValue(maintenance.getMaintenanceId(), maintenanceDTO.getMaintenanceId()));

		maintenance.setServiceDate(ControllerUtils.setValue(maintenance.getServiceDate(), maintenanceDTO.getServiceDate()));

		maintenance.setDescription(ControllerUtils.setValue(maintenance.getDescription(), maintenanceDTO.getDescription()));



        maintenance = maintenanceDao.save(maintenance);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MaintenanceDTO getMaintenanceDTOById(Integer maintenanceId) {
	
		Maintenance maintenance = maintenanceDao.getById(maintenanceId);
			
		
		MaintenanceConvertCriteriaDTO convertCriteria = new MaintenanceConvertCriteriaDTO();
		return(this.convertMaintenanceToMaintenanceDTO(maintenance,convertCriteria));
	}







}
