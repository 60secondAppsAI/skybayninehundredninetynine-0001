package com.skybayninehundredninetynine.dao;

import java.util.List;

import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.domain.Coupon;





public interface CouponDAO extends GenericDAO<Coupon, Integer> {
  
	List<Coupon> findAll();
	






}


