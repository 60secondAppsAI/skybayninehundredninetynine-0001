package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.Coupon;
import com.skybayninehundredninetynine.dto.CouponDTO;
import com.skybayninehundredninetynine.dto.CouponSearchDTO;
import com.skybayninehundredninetynine.dto.CouponPageDTO;
import com.skybayninehundredninetynine.dto.CouponConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CouponService extends GenericService<Coupon, Integer> {

	List<Coupon> findAll();

	ResultDTO addCoupon(CouponDTO couponDTO, RequestDTO requestDTO);

	ResultDTO updateCoupon(CouponDTO couponDTO, RequestDTO requestDTO);

    Page<Coupon> getAllCoupons(Pageable pageable);

    Page<Coupon> getAllCoupons(Specification<Coupon> spec, Pageable pageable);

	ResponseEntity<CouponPageDTO> getCoupons(CouponSearchDTO couponSearchDTO);
	
	List<CouponDTO> convertCouponsToCouponDTOs(List<Coupon> coupons, CouponConvertCriteriaDTO convertCriteria);

	CouponDTO getCouponDTOById(Integer couponId);







}





