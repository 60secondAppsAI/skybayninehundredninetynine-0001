package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Luggage;





public interface LuggageDAO extends GenericDAO<Luggage, Integer> {
  
	List<Luggage> findAll();
	






}


