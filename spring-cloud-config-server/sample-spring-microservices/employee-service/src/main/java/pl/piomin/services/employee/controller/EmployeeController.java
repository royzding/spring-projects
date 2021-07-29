package pl.piomin.services.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import pl.piomin.services.employee.model.Employee;
import pl.piomin.services.employee.repository.EmployeeRepository;

@RestController
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	EmployeeRepository repository;
	
	@GetMapping("/hi")
	public String hi() {
		LOGGER.info("Employee find");
		return "Hi Employee";
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
	public Employee add(@RequestBody Employee employee) {
		LOGGER.info("Employee add: {}", employee);
		return repository.add(employee);
	}
	
	@GetMapping("/{id}")
	public Employee findById(@PathVariable("id") Long id) {
		LOGGER.info("Employee find: id={}", id);
		return repository.findById(id);
	}
	
	@GetMapping("/")
	public List<Employee> findAll() {
		LOGGER.info("Employee find");
		return repository.findAll();
	}
	
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
	
}
