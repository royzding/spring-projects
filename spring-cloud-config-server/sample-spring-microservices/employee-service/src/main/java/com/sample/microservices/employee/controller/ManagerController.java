package com.sample.microservices.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.pagination.PageLayout;
import com.sample.microservices.employee.enums.ManagerSortType;
import com.sample.microservices.employee.service.ManagerService;
import com.sample.microservices.model.dto.ManagerDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/manager-controller")
public class ManagerController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
	
	private final ManagerService managerService;
	
	ManagerController(ManagerService managerService) {
		this.managerService = managerService;		
	}
	
	@Operation(summary="get an manager by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an Manager",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Manager.class)) }),
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public Manager getManagerById(@PathVariable("id") Long id) {
		return this.managerService.getManagerById(id);
	}
	
	@Operation(summary="get all the managers")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<Manager> getAllManagers() {
		return this.managerService.getAllManagers();
	}

	@Operation(summary="get all the managers with pagination and provided search filter-in")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all-managers")
	public PageLayout<Manager> getAllManagersWithPagination(
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value="sort", defaultValue = "ID") List<ManagerSortType> sort,
			@RequestParam(value="direction", defaultValue = "ASC") Sort.Direction direction
			) {
		return this.managerService.getAllManagersWithPagination(pageNum, pageSize, sort, direction);
	}

	@Operation(summary="get all the managers with pagination and provided search filter-in")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/name-in")
	public PageLayout<Manager> getAllManagersWithPaginationAndFilter(
			@Parameter(name="names", description="In: name(s) such as n1,n2, ... ...")
			@RequestParam(required=false) List<String> names,
			@RequestParam(value="pageNum", defaultValue = "1") int pageNum,
			@RequestParam(value="pageSize", defaultValue = "5") int pageSize,
			@RequestParam(value="sort", defaultValue = "ID") List<ManagerSortType> sort,
			@RequestParam(value="direction", defaultValue = "ASC") Sort.Direction direction
			) {
		return this.managerService.getAllManagersWithPaginationAndFilter(names, pageNum, pageSize, sort, direction);
	}

	@Operation(summary="Create an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Manager created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=Manager.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Manager with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public Manager createManager(@Valid @RequestBody ManagerDto managerDto) {

		return this.managerService.createManager(managerDto);
	}
	
	@Operation(summary="Create a list of managers")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="Managers created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=Manager.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="Manager with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Manager> createManagers(@Valid @RequestBody List<ManagerDto> managerDtos) {

		return this.managerService.createManagers(managerDtos);
	}
	
	@Operation(summary="Update an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateManager(@PathVariable("id") Long id, @Valid @RequestBody ManagerDto managerDto) throws Exception {
		this.managerService.updateManager(id, managerDto);
	}
	
	@Operation(summary="Delete an manager")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Manager deleted"), 
			@ApiResponse(responseCode="404",description="Manager does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteManager(@PathVariable("id") Long id) {

		this.managerService.deleteManagerById(id);
	}
	
	@Operation(summary="Delete all managers")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the Managers deleted"), 
			@ApiResponse(responseCode="404",description="Some Manager does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllManagers() {

		this.managerService.deleteAllManagers();
	}
	
}
