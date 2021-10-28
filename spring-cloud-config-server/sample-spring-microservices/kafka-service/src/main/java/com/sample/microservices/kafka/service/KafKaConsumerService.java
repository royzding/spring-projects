package com.sample.microservices.kafka.service;

import com.sample.microservices.kafka.model.User;

public interface KafKaConsumerService {
	void consume01OneTopic(String message);
	void consume02OneTopic(String message);
	void consumeMultipleTopics(String message);
	
	void listenWithFilter(String message);
	
	void consumeWithObject(User user);	
}
