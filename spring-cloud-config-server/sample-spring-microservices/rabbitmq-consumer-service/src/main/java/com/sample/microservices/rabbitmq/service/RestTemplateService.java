package com.sample.microservices.rabbitmq.service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sample.microservices.rabbitmq.model.Payload;

@Service
public class RestTemplateService {
/*	
    @Value("${rest.template.url}")
    private String URL;
    
    @Autowired
    ThreadPoolTaskExecutor threadPool;
    
    @Autowired
	private RestTemplate restTemplate;
    
    @Autowired
    private ObjectMapper objectMapper;
	
	public String execute(Payload payload) {
				
		try {
			
			System.out.println("Payload Object:" + payload);
			
			String jsonString = objectMapper.writeValueAsString(payload);
			
			System.out.println("Payload json String:" + jsonString);
			
			Payload payload2 = objectMapper.readValue(jsonString, Payload.class);
			
			System.out.println("Payload Object 2:" + payload2);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		Future<String> fs = threadPool.submit(()->this.restTemplate.getForObject(URL, String.class));
		
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
*/
}
