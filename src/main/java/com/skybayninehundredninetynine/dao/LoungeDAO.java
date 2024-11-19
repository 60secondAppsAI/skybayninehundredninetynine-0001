package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Lounge;





public interface LoungeDAO extends GenericDAO<Lounge, Integer> {
  
	List<Lounge> findAll();
	






}


