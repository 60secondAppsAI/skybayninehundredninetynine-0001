package com.skybayninehundredninetynine.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.skybayninehundredninetynine.domain.Meal;
import com.skybayninehundredninetynine.dto.MealDTO;
import com.skybayninehundredninetynine.dto.MealSearchDTO;
import com.skybayninehundredninetynine.dto.MealPageDTO;
import com.skybayninehundredninetynine.dto.MealConvertCriteriaDTO;
import com.skybayninehundredninetynine.service.GenericService;
import com.skybayninehundredninetynine.dto.common.RequestDTO;
import com.skybayninehundredninetynine.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MealService extends GenericService<Meal, Integer> {

	List<Meal> findAll();

	ResultDTO addMeal(MealDTO mealDTO, RequestDTO requestDTO);

	ResultDTO updateMeal(MealDTO mealDTO, RequestDTO requestDTO);

    Page<Meal> getAllMeals(Pageable pageable);

    Page<Meal> getAllMeals(Specification<Meal> spec, Pageable pageable);

	ResponseEntity<MealPageDTO> getMeals(MealSearchDTO mealSearchDTO);
	
	List<MealDTO> convertMealsToMealDTOs(List<Meal> meals, MealConvertCriteriaDTO convertCriteria);

	MealDTO getMealDTOById(Integer mealId);







}





