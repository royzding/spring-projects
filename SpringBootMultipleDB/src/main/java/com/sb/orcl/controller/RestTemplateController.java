package com.sb.orcl.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.sb.orcl.model.Test;

@RestController
@RequestMapping(value="/test")
public class RestTemplateController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping("/list")
	public String getTestList() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		return restTemplate.exchange("http://localhost:8080/test/list", HttpMethod.GET, 
				entity, String.class).getBody();
	}

	@GetMapping("/{id}")
	public String getById(@PathVariable("id") int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		return restTemplate.exchange("http://localhost:8080/test/"+id, HttpMethod.GET, 
				entity, String.class).getBody();
	}
		
	//curl -d "{\"name\":\"Johnx\", \"age\":\"20\"}" -H "Content-Type: application/json" -X POST http://localhost:8082/test/save
	@PostMapping("/save")
	public String saveTest(@RequestBody Test test) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Test> entity = new HttpEntity<>(test, headers);
		
		return //"Test Post";
				restTemplate.exchange("http://localhost:8080/test/save", HttpMethod.POST, 
				entity, String.class).getBody();
		
	}
	
	@PutMapping("/update/{id}") 
	public String update(@RequestBody Test test,@PathVariable("id") long id) {
		test.setId(id);  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Test> entity = new HttpEntity<>(test, headers);
		
		return 	restTemplate.exchange("http://localhost:8080/test/update/"+id, HttpMethod.PUT, 
				entity, String.class).getBody();
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id) {
	      HttpHeaders headers = new HttpHeaders();
	      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	      HttpEntity<Test> entity = new HttpEntity<>(headers);
	      
	      return restTemplate.exchange(
	         "http://localhost:8080/test/delete/"+id, HttpMethod.DELETE, entity, String.class).getBody();
	}
	

}

/*
@RequestMapping(value="/test")
public class TestController {
	
	@Autowired
	TestService testService;
	

    @PostMapping("/save")  
    public Test saveTest(@RequestBody Test test) {  
         return testService.save(test);  
          
    }  

	@GetMapping("/list") 
	public List<Test> list() 
	{
		return testService.findAll();
	}
	
	@GetMapping("/{id}")
	public Test get(@PathVariable("id") int id) {
		return testService.findById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id) {
		testService.delete(testService.findById(id));
	}
	
	@PostMapping("/update/{id}") 
	public Test update(@RequestBody Test test,@PathVariable("id") long id) {
		test.setId(id);  
		return testService.update(test);
	}
	

}
*/
