package com.sample.microservices.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {

	private Long id;
	private String name;
	private Double salary;
	private String modifiedBy;
	
	public Manager(String name, Double salary) {
		this.name = name;
		this.salary = salary;
	}
}
