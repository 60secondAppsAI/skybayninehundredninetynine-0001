package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.ReservationAgent;





public interface ReservationAgentDAO extends GenericDAO<ReservationAgent, Integer> {
  
	List<ReservationAgent> findAll();
	






}


