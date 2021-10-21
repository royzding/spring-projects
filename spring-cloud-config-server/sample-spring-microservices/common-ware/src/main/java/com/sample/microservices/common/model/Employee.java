package com.sample.microservices.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

	private Long id;
	private String name;
	private Double salary;

	private Long depId;
	private String depName;
	private Long managerId;
	private String managerName;
}
