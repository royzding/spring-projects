package com.sample.microservices.common.model.dto;

import java.math.BigDecimal;
import java.util.List;

import com.sample.microservices.common.enums.Gender;

import lombok.Data;

@Data
public class StudentDto {

	private String firstName;
	private String lastName;
	private boolean isActive;
	private Gender gender;
	private String email;
	private Address address;
	private List<String> courses;
	private BigDecimal salary;

}
