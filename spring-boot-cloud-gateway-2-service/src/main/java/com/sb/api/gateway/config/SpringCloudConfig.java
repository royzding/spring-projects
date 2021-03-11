package com.sb.api.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/student/**")
                        .uri("lb://STUDENT-SERVICE")
                        .id("studentService"))

                .route(r -> r.path("/course/**")
                        .uri("lb://COURSE-SERVICE")
                        .id("courseService"))
                .build();
    }

}