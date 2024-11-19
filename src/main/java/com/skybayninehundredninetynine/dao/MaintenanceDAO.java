package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Maintenance;





public interface MaintenanceDAO extends GenericDAO<Maintenance, Integer> {
  
	List<Maintenance> findAll();
	






}


