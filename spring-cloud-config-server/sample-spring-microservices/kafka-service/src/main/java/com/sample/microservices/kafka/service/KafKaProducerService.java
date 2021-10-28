package com.sample.microservices.kafka.service;

public interface KafKaProducerService {
	void sendMessageToTopic01(String message);
	void sendMessageToTopic02(String message);
}
