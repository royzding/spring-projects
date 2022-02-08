package com.sample.microservices.mvcmongodb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.mvcmongodb.intermicroservice.RestTemplateService;

@RestController
@RequestMapping("/inter-micro")
public class InterMicroserviceController {

  private final RestTemplateService restTemplateService;
  
  InterMicroserviceController(RestTemplateService restTemplateService) {
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
}
