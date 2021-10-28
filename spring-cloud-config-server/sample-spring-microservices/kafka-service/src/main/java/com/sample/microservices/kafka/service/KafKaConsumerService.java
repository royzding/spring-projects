package com.sample.microservices.kafka.service;

public interface KafKaConsumerService {
	void consume(String message);
	void consumeOneTopic(String message);
	void consumeMultipleTopics(String message);
}
