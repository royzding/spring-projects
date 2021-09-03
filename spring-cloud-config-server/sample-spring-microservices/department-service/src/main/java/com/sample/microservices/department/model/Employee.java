package com.sample.microservices.department.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Long id;
	private String firstName;
	private String lastName;
	private Double salary;

	private Long depId;
	private Long managerId;
}
