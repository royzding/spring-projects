package com.sample.microservices.mvcmongodb.config;

import java.time.Duration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class MvcMongodbConfig {

	@Bean
	@LoadBalanced
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
	 
		return builder
		.setConnectTimeout(Duration.ofMillis(3000))
		.setReadTimeout(Duration.ofMillis(3000))
		.build();
	
	}
	
}