package com.sample.microservices.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ManagerMini {

	private Long id;
	private String name;
	private Double salary;
	private String modifiedBy;
	private Boolean highSalary;
}
