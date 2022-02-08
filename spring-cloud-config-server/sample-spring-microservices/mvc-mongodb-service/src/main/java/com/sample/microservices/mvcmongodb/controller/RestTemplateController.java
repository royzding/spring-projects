package com.sample.microservices.mvcmongodb.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.mvcmongodb.intermicroservice.RestTemplateService;
import com.sample.microservices.mvcmongodb.intermicroservice.WebClientService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/rest-template")
public class RestTemplateController {

  private final RestTemplateService restTemplateService;

  RestTemplateController(RestTemplateService restTemplateService) {
	  this.restTemplateService = restTemplateService;
  }

  @GetMapping("/manager/{id}")
  public Manager getManagerById(@PathVariable("id") Long id) {
	  return this.restTemplateService.getManagerById(id);
  }

  @GetMapping("/employee/{id}")
  public Employee getEmployeeById(@PathVariable("id") Long id) {
	  return this.restTemplateService.getEmployeeById(id);
  }
  
  @GetMapping("/greeting/{name}")
  public String greeting(@PathVariable("name") String name) {
	  return this.restTemplateService.greeting(name);
  }
  
  @GetMapping("/all-entities-list")
  @ResponseBody
  public  ResponseEntity<List<StudentEntity>> findAllStudentEntityList() {
      return this.restTemplateService.findAllStudentEntityList();
  }
  
}
