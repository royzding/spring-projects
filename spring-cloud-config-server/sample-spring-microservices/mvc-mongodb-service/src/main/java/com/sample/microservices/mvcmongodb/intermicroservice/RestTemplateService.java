package com.sample.microservices.mvcmongodb.intermicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;

@Component
public class RestTemplateService {

	@Autowired
    private RestTemplate restTemplate;
    
	public Manager getManagerById(Long id) {
		
		return this.restTemplate.getForObject("http://employee-service/employee/manager-controller/" + id, Manager.class);
		
	}
	
	public Employee getEmployeeById(Long id) {
		
		return this.restTemplate.getForObject("http://employee-service/employee/employee-controller/" + id, Employee.class);
		
	}
	
}
