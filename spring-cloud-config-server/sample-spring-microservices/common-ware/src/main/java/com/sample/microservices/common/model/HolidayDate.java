package com.sample.microservices.common.model;

import lombok.Data;

@Data
public class HolidayDate {

	private Long id;
	private String name;
	private Integer day;
	private Integer month;
	private Integer year;

}