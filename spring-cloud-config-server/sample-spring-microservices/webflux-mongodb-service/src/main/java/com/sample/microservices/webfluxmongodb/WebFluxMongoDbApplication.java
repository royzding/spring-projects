package com.sample.microservices.webfluxmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "APIs", version = "1.0", description = "Documentation APIs v1.0"))
public class WebFluxMongoDbApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxMongoDbApplication.class, args);
    }
}