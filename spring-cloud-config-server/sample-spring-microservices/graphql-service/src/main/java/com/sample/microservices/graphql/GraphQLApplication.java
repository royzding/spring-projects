package com.sample.microservices.graphql;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class GraphQLApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(GraphQLApplication.class, args);
    }
}
