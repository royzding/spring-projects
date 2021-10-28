package com.sample.microservices.kafka.service;

import com.sample.microservices.kafka.model.User;

public interface KafKaProducerService {
	void sendMessageToTopic01(String message);
	void sendMessageToTopic02(String message);
	
	void sendMessageToTopic03WithObject(User user);	
}
