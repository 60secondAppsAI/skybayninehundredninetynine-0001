package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.SecurityCheck;





public interface SecurityCheckDAO extends GenericDAO<SecurityCheck, Integer> {
  
	List<SecurityCheck> findAll();
	






}


