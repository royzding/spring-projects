package com.sample.microservices.kafka.service;

import com.sample.microservices.kafka.model.User;

public interface KafKaProducerService {
	String sendMessageToTopic01(String message);
	String sendMessageToTopic02(String message);
	String sendMessageToTopic03(String message);
	String sendMessageToTopic04(String message);
	
	String sendMessageToTopic03(User user);	
	String sendMessageToTopic04(User user);	
}
