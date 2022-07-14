package com.sample.microservices.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeInfo {

	private Long id;
	private String name;
	private Double salary;

	private String depName;
	private String managerName;
	
	public EmployeeInfo(Long id, String name, Double salary, String managerName) {
		super();
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.managerName = managerName;
	}
	
}
