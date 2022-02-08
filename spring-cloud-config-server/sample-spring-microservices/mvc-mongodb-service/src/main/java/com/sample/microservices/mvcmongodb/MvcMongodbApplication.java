package com.sample.microservices.mvcmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.mvcmongodb","com.sample.microservices.common"})
@EnableDiscoveryClient
public class MvcMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcMongodbApplication.class, args);
	}

}
