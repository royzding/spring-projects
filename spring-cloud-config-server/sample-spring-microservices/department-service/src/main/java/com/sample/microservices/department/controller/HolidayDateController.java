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

import com.sample.microservices.common.model.HolidayDate;
import com.sample.microservices.department.model.dto.HolidayDateDto;
import com.sample.microservices.department.service.HolidayDateService;
import com.sample.microservices.department.validator.HolidayDateValidator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "HolidayDate Service", description = "REST API for HolidayDate Service.")
@RestController
@RequestMapping("/holiday-date")
public class HolidayDateController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HolidayDateController.class);
	
	private final HolidayDateValidator holidayDateValidator;
	private final HolidayDateService holidayDateService;
	
	HolidayDateController(HolidayDateService holidayDateService, HolidayDateValidator holidayDateValidator) {
		this.holidayDateService = holidayDateService;	
		this.holidayDateValidator = holidayDateValidator;
	}
	
	@Operation(summary="get an holidayDate by id")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. Returns an HolidayDate",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=HolidayDate.class)) }),
			@ApiResponse(responseCode="404",description="HolidayDate does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@GetMapping("/{id}")
	public HolidayDate getHolidayDateById(@PathVariable("id") Long id) {
		return this.holidayDateService.getHolidayDateById(id);
	}
	
	@Operation(summary="get all the holidayDates by name")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=HolidayDate.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/holiday/{name}")
	public List<HolidayDate> getHolidayDateByName(String name) {
		return this.holidayDateService.getHolidayDateByName(name);
	}

	@Operation(summary="get all the holidayDates")
	@ApiResponses(value= {
		@ApiResponse(responseCode="200",description="Success. An empty list is returned when no records are found",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=HolidayDate.class))) }),
		@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",content= @Content) 
	})
	@GetMapping("/all")
	public List<HolidayDate> getAllHolidayDates() {
		return this.holidayDateService.getAllHolidayDates();
	}

	@Operation(summary="Create an holidayDate")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="HolidayDate created",
					content= {@Content(mediaType="application/json", schema=@Schema(implementation=HolidayDate.class)) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="HolidayDate with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/")
	@ResponseStatus(HttpStatus.CREATED)
	public HolidayDate createHolidayDate(@Valid @RequestBody HolidayDateDto holidayDateDto) throws Exception {

		this.holidayDateValidator.validateHolidayDateUnique(holidayDateDto);

		return this.holidayDateService.createHolidayDate(holidayDateDto);
	}
	
	@Operation(summary="Create a list of holidayDates")
	@ApiResponses(value= {
			@ApiResponse(responseCode="201",description="HolidayDates created",
					content= {@Content(mediaType="application/json", array=@ArraySchema(schema=@Schema(implementation=HolidayDate.class))) }),
			@ApiResponse(responseCode="400",description="Bad request"), 
			@ApiResponse(responseCode="409",description="HolidayDate with the same name already exists"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PostMapping("/list")
	@ResponseStatus(HttpStatus.CREATED)
	public List<HolidayDate> createHolidayDates(@Valid @RequestBody List<HolidayDateDto> holidayDateDtos) {
		
		return this.holidayDateService.createHolidayDates(holidayDateDtos);
	}
	
	@Operation(summary="Update an holidayDate")
	@ApiResponses(value= {
			@ApiResponse(responseCode="204",description="Success. No Content"), 
			@ApiResponse(responseCode="404",description="HolidayDate does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void updateHolidayDate(@PathVariable("id") Long id, @Valid @RequestBody HolidayDateDto holidayDateDto) throws Exception {
		this.holidayDateService.updateHolidayDate(id, holidayDateDto);
	}
	
	@Operation(summary="Delete an holidayDate")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. HolidayDate deleted"), 
			@ApiResponse(responseCode="404",description="HolidayDate does not exist for the given id"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping("/{id}")
	public void deleteHolidayDate(@PathVariable("id") Long id) {

		this.holidayDateService.deleteHolidayDateById(id);
	}
	
	@Operation(summary="Delete all holidayDates")
	@ApiResponses(value= {
			@ApiResponse(responseCode="200",description="Success. All the HolidayDates deleted"), 
			@ApiResponse(responseCode="404",description="Some HolidayDate does not exist"), 
			@ApiResponse(responseCode="500",description="Internal Server Error. The server could not process the request",
			content= @Content) 
	})
	@DeleteMapping
	public void deleteAllHolidayDates() {

		this.holidayDateService.deleteAllHolidayDates();
	}

}
