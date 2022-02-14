package com.sample.microservices.webfluxpostgresql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication(scanBasePackages= {"com.sample.microservices.webfluxpostgresql","com.sample.microservices.common"})
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
@EnableDiscoveryClient
public class WebFluxPostgresqlApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxPostgresqlApplication.class, args);
    }
}

//https://dzone.com/articles/r2dbc-reactive-programming-with-spring-part-4

//-Dspring.profiles.active=local,flux-security

//swagger url
//http://localhost:8087/webfluxpostgresql/