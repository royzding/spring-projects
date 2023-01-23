package com.sample.microservices.common.enums;

public enum ActivityType {
	GET("get"),
	POST("post"),
	DELETE("delete");
	
	private String value;
	
	ActivityType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
