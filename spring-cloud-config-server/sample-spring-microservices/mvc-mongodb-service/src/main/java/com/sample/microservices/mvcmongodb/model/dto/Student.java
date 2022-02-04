package com.sample.microservices.mvcmongodb.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sample.microservices.mvcmongodb.enums.Gender;

import lombok.Data;

@Data
public class Student {

	private String firstName;
	private String lastName;
	private boolean isActive;
	private Gender gender;
	private List<String> courses;
	private BigDecimal salary;

}
