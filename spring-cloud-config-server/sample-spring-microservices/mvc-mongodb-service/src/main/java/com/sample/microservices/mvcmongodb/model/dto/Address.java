package com.sample.microservices.mvcmongodb.model.dto;

import lombok.Data;

@Data
public class Address {
	
	private String address;
	private String city;
	private String zipCode;
	private String country;

}
