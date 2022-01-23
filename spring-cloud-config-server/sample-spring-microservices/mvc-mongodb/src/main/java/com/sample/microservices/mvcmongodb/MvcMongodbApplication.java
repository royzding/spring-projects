package com.sample.microservices.mvcmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.mvcmongodb","com.sample.microservices.common"})
public class MvcMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcMongodbApplication.class, args);
	}

}
