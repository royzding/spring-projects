package com.sample.microservices.mvcmongodb.intermicroservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;

import reactor.core.publisher.Flux;

@Component
public class RestTemplateService {

	@Value("${inter-microservice.webfluxmongodb-service}")
	private String webfluxmongodbService;
	
	@Value("${inter-microservice.employee-service}")
	private String employeeService;
	
	@Autowired
    private RestTemplate restTemplate;
    
	public Manager getManagerById(Long id) {
		
		return this.restTemplate.getForObject(employeeService + "/manager-controller/" + id, Manager.class);		
	}
	
	public Employee getEmployeeById(Long id) {
		
		return this.restTemplate.getForObject(employeeService + "/employee-controller/" + id, Employee.class);		
	}
	
	public String greeting(String name) {
		return String.format("%s, %s!", 
				this.restTemplate.getForObject(webfluxmongodbService + "/greeting", String.class), 
				name);
	}	
	
	public ResponseEntity<List<StudentEntity>> findAllStudentEntityList() {
		return this.restTemplate.exchange(webfluxmongodbService + "/all-entities-list",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<StudentEntity>>(){});
	}	
	
	
}
