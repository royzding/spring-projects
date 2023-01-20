package com.sample.microservices.department.model.dto;

import lombok.Data;

@Data
public class HolidayDateDto {

	private Long id;
	private String name;
	private Integer day;
	private Integer month;
	private Integer year;

}