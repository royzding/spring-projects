package com.sample.microservices.mvcmongodb.controller;

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
@RequestMapping("/web-client")
public class WebClientController {

  private final WebClientService webClientService;

  WebClientController(WebClientService webClientService) {
	  this.webClientService = webClientService;
  }

  @GetMapping("/greeting/{name}")
  public Mono<String> greeting(@PathVariable("name") String name) {
	  return this.webClientService.greeting(name);
  }
  
  @GetMapping("/all-entities")
  @ResponseBody
  public Flux<StudentEntity> findAllStudentEntity() {
      return this.webClientService.findAllStudentEntity();
  }
  
}
