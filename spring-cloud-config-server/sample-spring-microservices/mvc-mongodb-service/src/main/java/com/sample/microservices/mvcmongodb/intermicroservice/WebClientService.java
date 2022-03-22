package com.sample.microservices.mvcmongodb.intermicroservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.reactive.function.client.WebClient;

import com.sample.microservices.common.data.model.StudentEntity;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class WebClientService {

	@Value("${inter-microservice.webflux-mongodb-service}")
	private String webfluxmongodbService;
	
	@Value("${inter-microservice.employee-service}")
	private String employeeService;
	
	private final ReactorLoadBalancerExchangeFilterFunction lbFunction;
	
	WebClientService(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
		this.lbFunction = lbFunction;		
	}
	
	public Mono<String> greeting(String name) {
	    return WebClient.builder()
	        .filter(lbFunction)
	        .build().get().uri(webfluxmongodbService + "/greeting")
	        .retrieve().bodyToMono(String.class)
	        .map(greeting -> String.format("%s, %s!", greeting, name));	  
	}	

	public Flux<StudentEntity> findAllStudentEntity() {
	    return WebClient.builder()
	        .filter(lbFunction)
	        .build().get().uri(webfluxmongodbService + "/all-entities")
	        .retrieve().bodyToFlux(StudentEntity.class);	  
	}	

}
