package com.skybayninehundredninetynine.dto;

import java.sql.Timestamp;
import java.time.Year;
import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReservationAgentSearchDTO {

	private Integer page = 0;
	private Integer size;
	private String sortBy;
	private String sortOrder;
	private String searchQuery;

	private Integer reservationAgentId;
	
	private String name;
	
	private String employeeNumber;
	
	private String contactEmail;
	
}
