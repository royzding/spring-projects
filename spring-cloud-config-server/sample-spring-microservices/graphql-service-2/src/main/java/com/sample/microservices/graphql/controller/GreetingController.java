package com.sample.microservices.graphql.controller;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.sample.microservices.graphql.repository.DataRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class GreetingController {

	private final DataRepository repository;

	public GreetingController(DataRepository dataRepository) {
		this.repository = dataRepository;
	}

	@QueryMapping
	public String greeting() {
		return this.repository.getBasic();
	}

	@QueryMapping
	public Mono<String> greetingMono() {
		return this.repository.getGreeting();
	}

	@QueryMapping
	public Flux<String> greetingsFlux() {
		return this.repository.getGreetings();
	}

	@SubscriptionMapping
	public Flux<String> greetings() {
		return this.repository.getGreetingsStream();
	}

}
