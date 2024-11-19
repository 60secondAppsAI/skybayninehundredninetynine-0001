package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Airplane;





public interface AirplaneDAO extends GenericDAO<Airplane, Integer> {
  
	List<Airplane> findAll();
	






}


