package com.skybayninehundredninetynine.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.skybayninehundredninetynine.dao.GenericDAO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.service.impl.GenericServiceImpl;
import com.skybayninehundredninetynine.dao.CouponDAO;
import com.skybayninehundredninetynine.domain.Coupon;
import com.skybayninehundredninetynine.dto.CouponDTO;
import com.skybayninehundredninetynine.dto.CouponSearchDTO;
import com.skybayninehundredninetynine.dto.CouponPageDTO;
import com.skybayninehundredninetynine.dto.CouponConvertCriteriaDTO;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import com.skybayninehundredninetynine.service.CouponService;
import com.skybayninehundredninetynine.util.ControllerUtils;





@Service
public class CouponServiceImpl extends GenericServiceImpl<Coupon, Integer> implements CouponService {

    private final static Logger logger = LoggerFactory.getLogger(CouponServiceImpl.class);

	@Autowired
	CouponDAO couponDao;

	


	@Override
	public GenericDAO<Coupon, Integer> getDAO() {
		return (GenericDAO<Coupon, Integer>) couponDao;
	}
	
	public List<Coupon> findAll () {
		List<Coupon> coupons = couponDao.findAll();
		
		return coupons;	
		
	}

	public ResultDTO addCoupon(CouponDTO couponDTO, RequestDTO requestDTO) {

		Coupon coupon = new Coupon();

		coupon.setCouponId(couponDTO.getCouponId());


		coupon.setCode(couponDTO.getCode());


		coupon.setDiscountPercentage(couponDTO.getDiscountPercentage());


		coupon.setExpirationDate(couponDTO.getExpirationDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		coupon = couponDao.save(coupon);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Coupon> getAllCoupons(Pageable pageable) {
		return couponDao.findAll(pageable);
	}

	public Page<Coupon> getAllCoupons(Specification<Coupon> spec, Pageable pageable) {
		return couponDao.findAll(spec, pageable);
	}

	public ResponseEntity<CouponPageDTO> getCoupons(CouponSearchDTO couponSearchDTO) {
	
			Integer couponId = couponSearchDTO.getCouponId(); 
 			String code = couponSearchDTO.getCode(); 
    			String sortBy = couponSearchDTO.getSortBy();
			String sortOrder = couponSearchDTO.getSortOrder();
			String searchQuery = couponSearchDTO.getSearchQuery();
			Integer page = couponSearchDTO.getPage();
			Integer size = couponSearchDTO.getSize();

	        Specification<Coupon> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, couponId, "couponId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, code, "code"); 
			
			
 			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("code")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Coupon> coupons = this.getAllCoupons(spec, pageable);
		
		//System.out.println(String.valueOf(coupons.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(coupons.getTotalPages()));
		
		List<Coupon> couponsList = coupons.getContent();
		
		CouponConvertCriteriaDTO convertCriteria = new CouponConvertCriteriaDTO();
		List<CouponDTO> couponDTOs = this.convertCouponsToCouponDTOs(couponsList,convertCriteria);
		
		CouponPageDTO couponPageDTO = new CouponPageDTO();
		couponPageDTO.setCoupons(couponDTOs);
		couponPageDTO.setTotalElements(coupons.getTotalElements());
		return ResponseEntity.ok(couponPageDTO);
	}

	public List<CouponDTO> convertCouponsToCouponDTOs(List<Coupon> coupons, CouponConvertCriteriaDTO convertCriteria) {
		
		List<CouponDTO> couponDTOs = new ArrayList<CouponDTO>();
		
		for (Coupon coupon : coupons) {
			couponDTOs.add(convertCouponToCouponDTO(coupon,convertCriteria));
		}
		
		return couponDTOs;

	}
	
	public CouponDTO convertCouponToCouponDTO(Coupon coupon, CouponConvertCriteriaDTO convertCriteria) {
		
		CouponDTO couponDTO = new CouponDTO();
		
		couponDTO.setCouponId(coupon.getCouponId());

	
		couponDTO.setCode(coupon.getCode());

	
		couponDTO.setDiscountPercentage(coupon.getDiscountPercentage());

	
		couponDTO.setExpirationDate(coupon.getExpirationDate());

	

		
		return couponDTO;
	}

	public ResultDTO updateCoupon(CouponDTO couponDTO, RequestDTO requestDTO) {
		
		Coupon coupon = couponDao.getById(couponDTO.getCouponId());

		coupon.setCouponId(ControllerUtils.setValue(coupon.getCouponId(), couponDTO.getCouponId()));

		coupon.setCode(ControllerUtils.setValue(coupon.getCode(), couponDTO.getCode()));

		coupon.setDiscountPercentage(ControllerUtils.setValue(coupon.getDiscountPercentage(), couponDTO.getDiscountPercentage()));

		coupon.setExpirationDate(ControllerUtils.setValue(coupon.getExpirationDate(), couponDTO.getExpirationDate()));



        coupon = couponDao.save(coupon);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CouponDTO getCouponDTOById(Integer couponId) {
	
		Coupon coupon = couponDao.getById(couponId);
			
		
		CouponConvertCriteriaDTO convertCriteria = new CouponConvertCriteriaDTO();
		return(this.convertCouponToCouponDTO(coupon,convertCriteria));
	}







}
