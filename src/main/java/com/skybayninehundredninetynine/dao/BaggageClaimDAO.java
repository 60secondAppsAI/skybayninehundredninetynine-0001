package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.BaggageClaim;





public interface BaggageClaimDAO extends GenericDAO<BaggageClaim, Integer> {
  
	List<BaggageClaim> findAll();
	






}


