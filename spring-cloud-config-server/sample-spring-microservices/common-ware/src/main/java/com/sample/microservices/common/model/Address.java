package com.sample.microservices.common.model;

import lombok.Data;

@Data
public class Address {
	
	private String street;
	private String city;
	private String zipCode;
	private String country;

}
