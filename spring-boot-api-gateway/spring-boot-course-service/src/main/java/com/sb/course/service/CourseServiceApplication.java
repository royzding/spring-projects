package com.sb.course.service;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@RequestMapping("/course")
public class CourseServiceApplication {

	@RequestMapping(value = "/courseName/{name}")
	public String echoStudentName(@PathVariable(name = "name") String name) {
		return "Hello  " + name + " Responsed on : " + new Date();
	}

	@RequestMapping(value = "/getCourseDetails/{name}")
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
