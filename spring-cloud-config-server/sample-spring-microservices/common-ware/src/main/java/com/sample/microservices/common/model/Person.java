package com.sample.microservices.common.model;

import java.util.Map;

import lombok.Data;

@Data
public class Person {
	
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	
	private Address addr;
	
	private Map<String, String> attrMap;

}
