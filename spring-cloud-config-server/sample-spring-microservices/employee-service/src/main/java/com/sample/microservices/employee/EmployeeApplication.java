package com.sample.microservices.employee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;


@SpringBootApplication(scanBasePackages= {"com.sample.microservices.employee","com.sample.microservices.common"})
@EnableDiscoveryClient
@EnableFeignClients
@OpenAPIDefinition(
		servers = { 
			@Server(url = "http://localhost:8082/employee"),
			@Server(url = "http://localhost:8083/employee") 
		}, 
		info = @Info(title = "employee-service", 
		version = "v3", 
		description = "Employee-Service APIs", 
		license = @License(name = "Apache 2.0", url = "http://foo.bar"), 
		contact = @Contact(url = "http://employee-server.com", 
		name = "Roy", 
		email = "Roy@example.com"))
)

public class EmployeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeApplication.class, args);
	}

/*	
//import io.swagger.v3.oas.models.*;

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI().addServersItem(new Server().url("https://myserver.com"))
				.components(new Components().addSecuritySchemes("basicScheme",
						new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("basic")))
				.info(new Info().title("SpringShop API").version("V0")
						.license(new License().name("Apache 2.0").url("http://springdoc.org")));
	}	
*/
	
}

//http://localhost:8083/employee-service/v3/swagger-ui.html
//-Dspring.profiles.active=local