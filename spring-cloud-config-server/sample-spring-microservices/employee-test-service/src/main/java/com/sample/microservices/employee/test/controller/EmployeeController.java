package com.sample.microservices.employee.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.employee.test.model.EmployeeEntity;
import com.sample.microservices.employee.test.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/all")
	public List<EmployeeEntity> getAllEmployeeEntitys() {
		
		return this.employeeService.getAllEmployees();

	}

}
