package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.FrequentFlyer;





public interface FrequentFlyerDAO extends GenericDAO<FrequentFlyer, Integer> {
  
	List<FrequentFlyer> findAll();
	






}


