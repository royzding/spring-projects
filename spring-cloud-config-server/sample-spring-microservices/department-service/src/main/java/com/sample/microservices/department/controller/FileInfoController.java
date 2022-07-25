package com.sample.microservices.department.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.model.Department;
import com.sample.microservices.common.util.UtilFuns;
import com.sample.microservices.department.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Department Service", description = "REST API for Department Service.")
@RestController
@RequestMapping("/release-info")
public class FileInfoController {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileInfoController.class);
	
	private final ResourceLoader resourceLoader;
	
	FileInfoController(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;		
	}
	
	@Operation(summary="get an department by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success"),
			@ApiResponse(responseCode="404",description="Department does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping
	public String getFileInfo() throws IOException {
		return UtilFuns.getFileInfo(resourceLoader, "classpath:fileInfo.json");
	}
}
