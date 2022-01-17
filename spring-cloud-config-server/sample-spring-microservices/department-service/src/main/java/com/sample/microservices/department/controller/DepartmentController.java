package com.sample.microservices.department.controller;

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

import com.sample.microservices.common.model.Department;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.department.model.dto.DepartmentDto;
import com.sample.microservices.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Department Service", description = "REST API for Department Service.")
@RestController
@RequestMapping("/department-controller")
public class DepartmentController {

	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	private final DepartmentService departmentService;
	
	DepartmentController(DepartmentService departmentService) {
		this.departmentService = departmentService;		
	}
	
	@Operation(summary="get an department by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Department",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Department.class)) }),
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Department getDepartmentById(@PathVariable("id") Long id) {
		return this.departmentService.getDepartmentById(id);
	}
	
	@Operation(summary="get all the departments")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Department.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Department> getAllDepartments() {
		return this.departmentService.getAllDepartments();
	}

	@Operation(summary="Create an department")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Department created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Department.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Department with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Department createDepartment(@Valid @RequestBody DepartmentDto departmentDto) {

		return this.departmentService.createDepartment(departmentDto);
	}
	
	@Operation(summary="Create a list of departments")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Departments created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Department.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Department with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Department> createDepartments(@Valid @RequestBody List<DepartmentDto> departmentDtos) {

		return this.departmentService.createDepartments(departmentDtos);
	}
	
	@Operation(summary="Update an department")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateDepartment(@PathVariable("id") Long id, @Valid @RequestBody DepartmentDto departmentDto) throws Exception {
		this.departmentService.updateDepartment(id, departmentDto);
	}
	
	@Operation(summary="Delete an department")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Department deleted"), 
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteDepartment(@PathVariable("id") Long id) {

		this.departmentService.deleteDepartmentById(id);
	}
	
	@Operation(summary="Delete all departments")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Departments deleted"), 
			@ApiResponse(responseCode="404",description="Some Department does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllDepartments() {

		this.departmentService.deleteAllDepartments();
	}

	@Operation(summary="get employees by depId")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Employee.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/employees/{depId}")
	public List<Employee> getEmployeesByDepartmentId(@PathVariable("depId") Long depId) {
		return this.departmentService.getEmployeesByDepartmentId(depId);
	}

	
/*	
	@Operation(summary="get a list of department of the department")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Department.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/department/{departmentId}")
	public List<Department> findByDepartment(@PathVariable("departmentId") Long departmentId) {
		LOGGER.info("Department find: departmentId={}", departmentId);
		return repository.findByDepartment(departmentId);
	}
	
	@GetMapping("/organization/{organizationId}")
	public List<Department> findByOrganization(@PathVariable("organizationId") Long organizationId) {
		LOGGER.info("Department find: organizationId={}", organizationId);
		return repository.findByOrganization(organizationId);
	}
*/	
}
