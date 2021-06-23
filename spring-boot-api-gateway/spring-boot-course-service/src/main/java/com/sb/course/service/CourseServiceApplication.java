package com.sb.course.service;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RequestMapping("/course")
public class CourseServiceApplication {

    @Operation(summary = "Get course name")
    @ApiResponses(value = { 
    	     @ApiResponse(responseCode = "200", description = "successful operation", content = @Content), 
    	     @ApiResponse(responseCode = "500", description = "internal server error", content = @Content) 
    })
    @GetMapping("/courseName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name) {
		return "Hello  " + name + " Responsed on : " + new Date();
	}

    @GetMapping("/getCourseDetails/{name}")
	public Course getStudentDetails(@PathVariable(name = "name") String name) {
		return new Course(name, "Building A", "Class 101");
	}

	public static void main(String[] args) {
		SpringApplication.run(CourseServiceApplication.class, args);
	}
}

class Course {
	String name;
	String address;
	String cls;

	public Course(String name, String address, String cls) {
		super();
		this.name = name;
		this.address = address;
		this.cls = cls;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getCls() {
		return cls;
	}

}
