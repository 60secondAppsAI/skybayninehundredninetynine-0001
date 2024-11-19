package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Airport;





public interface AirportDAO extends GenericDAO<Airport, Integer> {
  
	List<Airport> findAll();
	






}


