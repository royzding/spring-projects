package com.test.rabbitmq.consumer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.test.rabbitmq.consumer.model.Payload;

@Service
public class RestTemplateService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private String URL;
	
	public String execute(Payload payload) {
		
		return this.restTemplate.getForObject(URL, String.class);
	}

}
