package com.sample.microservices.employee.enums;

public enum ManagerSortType {
	ID("id"),
	NAME("name"),
	SALARY("salary");
	
	private String value;
	
	ManagerSortType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
