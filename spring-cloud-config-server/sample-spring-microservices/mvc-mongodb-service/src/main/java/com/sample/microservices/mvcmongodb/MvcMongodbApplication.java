package com.sample.microservices.mvcmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.mvcmongodb","com.sample.microservices.common"})
@EnableDiscoveryClient
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/mvc-mongodb"),
			@Server(url = "http://localhost:8085/mvc-mongodb") 
		}, 
		info = @Info(title = "mvc-mongodb-service", 
		version = "${springdoc.open-api.version}", 
		description = "mvc-mongodb-Service APIs", 
		license = @License(name = "${springdoc.open-api.license.name}", url = "http://foo.bar"), 
		contact = @Contact(url = "http://mvc-mongodb-server.com", 
		name = "${springdoc.open-api.name}", 
		email = "${springdoc.open-api.email}"))
)
public class MvcMongodbApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvcMongodbApplication.class, args);
	}

}
