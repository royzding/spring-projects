package com.sample.microservices.circuitbreaker.service;

import reactor.core.publisher.Mono;

public interface SlowService {
	
	Mono<String> slowMethod(long delay);

}
