package com.sample.microservices.employee.enums;

public enum DepartmentType {
	MATH("math"),
	CHEMISTRY("chemistry"),
	PHYSICS("physics");
	
	private String value;
	
	DepartmentType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
