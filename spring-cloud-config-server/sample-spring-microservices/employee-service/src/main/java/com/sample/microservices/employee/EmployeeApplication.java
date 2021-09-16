package com.sample.microservices.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.employee","com.sample.microservices.common"})
@EnableDiscoveryClient
@EnableFeignClients
public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

}

//http://localhost:8083/employee-service/v3/swagger-ui.html
//-Dspring.profiles.active=local