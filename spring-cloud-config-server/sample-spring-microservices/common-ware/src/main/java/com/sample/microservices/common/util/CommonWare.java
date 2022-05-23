package com.sample.microservices.common.util;

import com.sample.microservices.common.model.Address;
import com.sample.microservices.common.model.Person;

public class CommonWare {

	public static void main(String[] args) {
		
		System.out.println("x".equalsIgnoreCase(null));

		Person person = new Person();
		person.setId(1L);
		person.setFirstName("first name");
		person.setLastName("last name");
		person.setEmail("p@abc.com");

		Address addr = new Address();
		addr.setStreet("1 Main Str");
		addr.setCity("Monroe");
		addr.setCountry("US");
		addr.setZipCode("06468");
		
		person.setAddr(addr);

		
		System.out.println(JsonConversions.objectToJsonStr(person));
				
		System.out.println(JsonConversions.jsonStrToObject(JsonConversions.objectToJsonStr(person), Person.class));


	}

}
