package com.async.test.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.async.test.data.model.EmployeeEntity;
import com.async.test.model.Employee;
import com.async.test.model.dto.EmployeeDto;
import com.async.test.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	private final EmployeeService EmployeeService;
	
	EmployeeController(EmployeeService EmployeeService) {
		this.EmployeeService = EmployeeService;		
	}
	
	@GetMapping("/cacheable-time")
	public Long getCacheableTime() {
		LOGGER.info("getCacheableTime Methods.");

		StopWatch sw = new StopWatch();
		
		sw.start();
		
		Long time = this.EmployeeService.getCacheableTime();
		
		sw.stop();
		
		System.out.println("time taking in controller:" + sw.getTotalTimeMillis());

		return time;
	}
		
	@Operation(summary="get a list of employee")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		LOGGER.info("getAllEmployees Methods.");
				
		StopWatch sw = new StopWatch();
		
		sw.start();
		
		 List<Employee> employees = this.EmployeeService.getAllEmployees();
		
		sw.stop();
		
		System.out.println("time taking in controller getAllEmployees:" + sw.getTotalTimeMillis());

		return employees;
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		LOGGER.info("getEmployeeById Methods.");
				
		return this.EmployeeService.getEmployee(id);
	}
	
	@Operation(summary="Create an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Employee created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Employee.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Employee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody() EmployeeDto employee) {
		LOGGER.info("createEmployee Methods.");
				
		return this.EmployeeService.createEmployee(employee);
	}

	@DeleteMapping("/{id}")
	public void deleteEmployeeById(@PathVariable("id") Long id) {
		LOGGER.info("deleteEmployeeById Methods.");
				
		this.EmployeeService.deleteEmployee(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody() EmployeeDto employee) {
		LOGGER.info("createEmployee Methods.");
				
		this.EmployeeService.updateEmployee(id, employee);
	}

	
}