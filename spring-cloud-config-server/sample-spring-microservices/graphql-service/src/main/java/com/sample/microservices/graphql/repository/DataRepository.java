package com.sample.microservices.graphql.repository;

import java.time.Duration;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.stereotype.Repository;

/**
 * Repository with data fetcher methods.
 */
@Repository
public class DataRepository {

	public String getBasic() {
		return "Hello world!";
	}

	public Mono<String> getGreeting() {
		return Mono.delay(Duration.ofMillis(50)).map(aLong -> "Hello!");
	}

	public Flux<String> getGreetings() {
		return Mono.delay(Duration.ofMillis(50))
				.flatMapMany(aLong -> Flux.just("Hi!", "Bonjour!", "Hola!", "Ciao!", "Zdravo!"));
	}

	public Flux<String> getGreetingsStream() {
		return Mono.delay(Duration.ofMillis(50))
				.flatMapMany(aLong -> Flux.just("Hi!", "Bonjour!", "Hola!", "Ciao!", "Zdravo!"));
	}

}
