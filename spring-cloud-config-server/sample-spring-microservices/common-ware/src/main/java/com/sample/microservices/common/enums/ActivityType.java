package com.sample.microservices.common.enums;

public enum ActivityType {
	GET("get"),
	POST("post"),
	DELETE("delete");
	
	private final String value;
	
	ActivityType(final String value) {
		this.value = value;
	}
	
	public String getValue() {
		return this.value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}

}
