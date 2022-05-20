package com.sample.microservices.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.common.model.Address;
import com.sample.microservices.common.model.Person;

public class JsonConversions {
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {

		Person person1 = new Person();
		person1.setId(1L);
		person1.setFirstName("first name");
		person1.setLastName("last name");
		person1.setEmail("p@abc.com");

		Address addr = new Address();
		addr.setStreet("1 Main Str");
		addr.setCity("Monroe");
		addr.setCountry("US");
		addr.setZipCode("06468");
		
		person1.setAddr(addr);
		
		Map<String, String> aMap = new HashMap<>();
		aMap.put("k1", "v1");
		aMap.put("k2", "v2");
		aMap.put("k3", "v3");
		
		person1.setAttrMap(aMap);

		//convertPerson(person1);
		
		Person person2 = new Person();
		person2.setId(2L);
		person2.setFirstName("first name 2");
		person2.setLastName("last name 2");
		person2.setEmail("p2@abc.com");

		Address addr2 = new Address();
		addr.setStreet("2 Main Str");
		addr.setCity("Monroe");
		addr.setCountry("US");
		addr.setZipCode("06468");
		
		person2.setAddr(addr2);
		
		Map<String, String> aMap2 = new HashMap<>();
		aMap2.put("k1", "v1");
		aMap2.put("k2", "v2");
		aMap2.put("k3", "v3");
		
		person2.setAttrMap(aMap2);
		
		List<Person> personList = new ArrayList<>();
		personList.add(person1);
		personList.add(person2);
		
		convertPersonList(personList);
		


	}

	private JsonConversions() {

	}
	
	public static void convertPersonList(List<Person> personList) {
		
		String pStr1 = JsonConversions.objectToJsonStr(personList);
		System.out.println(pStr1);
		
		String pStr2 = JsonConversions.objectToJsonPrettyStr(personList);
		System.out.println(pStr2);
		
		@SuppressWarnings("unchecked")
		List<Person> pList = JsonConversions.jsonStrToObject(pStr1, List.class); //new ArrayList<Person>().getClass());
				
		System.out.println(pList);
		
		personList.forEach(l->System.out.println(l));
		
		List<Person> pList2 = Arrays.asList(JsonConversions.jsonStrToObject(pStr1, Person[].class)); 
		
		System.out.println(pList2);
	}
	
	public static void convertPerson(Person person) {
		
		String pStr1 = JsonConversions.objectToJsonStr(person);
		System.out.println(pStr1);
		
		String pStr2 = JsonConversions.objectToJsonPrettyStr(person);
		System.out.println(pStr2);
		
		Person p1 = JsonConversions.jsonStrToObject(JsonConversions.objectToJsonStr(person), Person.class);
				
		System.out.println(p1);
		
		Map<String, Object> objMap = JsonConversions.jsonStrToMapObject(pStr1);
		
		String fName = (String)objMap.get("firstName");
		
		System.out.println(fName);

		Object addrObj = objMap.get("addr");
		
		@SuppressWarnings("unchecked")
		Map<String, String> addr1 = (HashMap<String,String>)addrObj;
		
		addr1.forEach((k,v)->System.out.println(k + ":" + v));
		
		
		System.out.println(objMap.get("addr"));
		
		System.out.println(objMap.get("addrx"));
		
		
		Map<String,String> attrMap = (HashMap)objMap.get("attrMap");
		
		attrMap.forEach((k,v)->System.out.println(k + ":" + v));
		
	}
	
	public static String objectToJsonStr(Object obj) {
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writeValueAsString(obj);
		}
		catch (JsonProcessingException  e) {
		    // catch various errors
		    e.printStackTrace();
		} 
		
		return null;
	}

	public static String objectToJsonPrettyStr(Object obj) {
		
		try {
		    // convert user object to json string and return it 
		    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		}
		catch (JsonProcessingException  e) {
		    // catch various errors
		    e.printStackTrace();
		} 
		
		return null;
	}

	public static <T> T jsonStrToObject(final String jsonStr, final Class<T> objectClass) {
		
		try {
			
		    // convert json string to object and return it 
			return mapper.readValue(jsonStr, objectClass);

		}
		catch (JsonProcessingException  e) {
		    // catch various errors
		    e.printStackTrace();
		} 
		
		return null;
	}

	public static <T> Map<String, T> jsonStrToMapObject(final String jsonStr) {
		
		try {
			
		    // convert json string to object and return it 
			return mapper.readValue(jsonStr, new TypeReference<Map<String, T>>(){});

		}
		catch (JsonProcessingException  e) {
		    // catch various errors
		    e.printStackTrace();
		} 
		
		return null;
	}

}


