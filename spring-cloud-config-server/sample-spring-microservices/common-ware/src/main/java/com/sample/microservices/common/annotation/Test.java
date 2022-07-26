package com.sample.microservices.common.annotation;

public class Test {

	public static void main(String[] args) throws JsonSerializeException {
				
		Car car = new Car("Ford", "F150", 2018);
		JsonSerializer serializer = new JsonSerializer();
		
		String carStr = serializer.serialize(car);
		
		System.out.println(carStr);

	}

}
