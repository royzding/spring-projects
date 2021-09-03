package com.sample.microservices.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.employee.model.Employee;
import com.sample.microservices.employee.service.EmployeeService;
import com.sample.microservices.model.dto.EmployeeDto;

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
	
	private final EmployeeService employeeService;
	
	EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;		
	}
	
	@Operation(summary="get an employee by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Employee",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Employee.class)) }),
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") Long id) {
		return this.employeeService.getEmployeeById(id);
	}
	
	@Operation(summary="get all the employees")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Employee> getAllEmployees() {
		return this.employeeService.getAllEmployees();
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
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {

		return this.employeeService.createEmployee(employeeDto);
	}
	
	@Operation(summary="Create a list of employees")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Employees created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Employee with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Employee> createEmployees(@Valid @RequestBody List<EmployeeDto> employeeDtos) {

		return this.employeeService.createEmployees(employeeDtos);
	}
	
	@Operation(summary="Update an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateEmployee(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDto employeeDto) throws Exception {
		this.employeeService.updateEmployee(id, employeeDto);
	}
	
	@Operation(summary="Delete an employee")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Employee deleted"), 
			@ApiResponse(responseCode="404",description="Employee does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") Long id) {

		this.employeeService.deleteEmployeeById(id);
	}
	
	@Operation(summary="Delete all employees")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Employees deleted"), 
			@ApiResponse(responseCode="404",description="Some Employee does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllEmployees() {

		this.employeeService.deleteAllEmployees();
	}
	
/*	
	@Operation(summary="get a list of employee of the department")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/department/{departmentId}")
	public List<Employee> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Employee find: departmentId={}", departmentId);
		return repository.findByDepartment(departmentId);
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Employee> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Employee find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}
*/	
}
