package com.sample.microservices.department;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices"})
@EnableDiscoveryClient
@EnableFeignClients

@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/department"),
			@Server(url = "http://localhost:8084/department") 
		}, 
		info = @Info(title = "department-service", 
		version = "v3", 
		description = "Department-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://gigantic-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class DepartmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(DepartmentApplication.class, args);
	}
}


//http://localhost:8082/department-service/v3/swagger-ui.html