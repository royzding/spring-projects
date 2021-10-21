package com.sample.microservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDto {
	
	private Long id;
	private String name;
	private Double salary;

	private Long depId;
	private Long managerId;
}