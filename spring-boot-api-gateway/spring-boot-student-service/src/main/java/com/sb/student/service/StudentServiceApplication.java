package com.sb.student.service;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@SpringBootApplication
@EnableEurekaClient
@RequestMapping("/student")
public class StudentServiceApplication {

    @Operation(summary = "Get Student name")
    @ApiResponses(value = { 
    	     @ApiResponse(responseCode = "200", description = "successful operation", content = @Content), 
    	     @ApiResponse(responseCode = "500", description = "internal server error", content = @Content) 
    })
	@GetMapping("/studentName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name) {
		return "Hello  " + name + " Responsed on : " + new Date();
	}

    @GetMapping("/getStudentDetails/{name}")
	public Student getStudentDetails(@PathVariable(name = "name") String name) {
		return new Student(name, "Pune", "MCA");
	}

    @GetMapping("/header")
    public String test(@RequestHeader("X-first-Header") String headerValue){
        return headerValue;
    }

    public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
}

class Student {
	String name;
	String address;
	String cls;

	public Student(String name, String address, String cls) {
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
