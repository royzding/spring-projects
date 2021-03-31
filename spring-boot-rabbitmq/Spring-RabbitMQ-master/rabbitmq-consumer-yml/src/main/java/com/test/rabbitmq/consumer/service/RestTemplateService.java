package com.test.rabbitmq.consumer.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.rabbitmq.consumer.model.Payload;

@Service
public class RestTemplateService {
	
    @Autowired
    ThreadPoolTaskExecutor threadPool;
    
    @Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private String URL;
	
	public String execute(Payload payload) {
		
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			String jsonString = mapper.writeValueAsString(payload);
			
			Payload payload2 = mapper.readValue(jsonString, Payload.class);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		Future<String> fs = threadPool.submit(
				()-> { return this.restTemplate.getForObject(URL, String.class); }
		);
		
		String statusStr = null;
		
		try {
			
			statusStr = fs.get();
			
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//return this.restTemplate.getForObject(URL, String.class);
		
		return statusStr;
	}

}
