package com.sample.microservices.common.annotation.test;

import org.slf4j.event.Level;

import com.sample.microservices.common.annotation.Loggable;

public class TestJsonField {

	public static void main(String[] args) throws JsonSerializeException {
				
		Car car = new Car("Ford", "F150", 2018);
		JsonSerializer serializer = new JsonSerializer();
		
		String carStr = serializer.serialize(car);
		
		System.out.println(carStr);
		
		testString("foo");

	}
	
	@Loggable
	public static boolean testString(String test) {
	  return test.equals("foo"); 
	} 	

}
