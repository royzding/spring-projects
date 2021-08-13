package com.async.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.async.test.model.Employee;
import com.async.test.service.EmployeeService;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService EmployeeService;
	
	EmployeeController(EmployeeService EmployeeService) {
		this.EmployeeService = EmployeeService;		
	}
		
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		LOGGER.info("Test Async Methods.");
				
		return this.EmployeeService.getAllEmployees();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		LOGGER.info("Test Async Methods.");
				
		return this.EmployeeService.getEmployee(id);
	}
	
	@PostMapping
	public Employee createEmployee(@Valid @RequestBody() Employee employee) {
		LOGGER.info("Test Async Methods.");
				
		return this.EmployeeService.createEmployee(employee);
	}
	
}