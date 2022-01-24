package com.sample.microservices.circuitbreaker.service;

import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class SlowServiceImpl implements SlowService{
	
	@Override
	public Mono<String> slowMethod(long delay) {
		
		try {
			
			Thread.sleep(delay*1000);
			
		} catch (InterruptedException e) {

		}
		
		return Mono.just("OK" + delay);
		
	}

	
}
