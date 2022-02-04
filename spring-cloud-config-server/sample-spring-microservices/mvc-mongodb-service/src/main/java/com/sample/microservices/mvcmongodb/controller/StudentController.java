package com.sample.microservices.mvcmongodb.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.mvcmongodb.model.StudentEntity;
import com.sample.microservices.mvcmongodb.model.dto.StudentDto;
import com.sample.microservices.mvcmongodb.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

  private final StudentService studentService;
  
  StudentController(StudentService studentService) {
	  this.studentService = studentService;
  }

  @GetMapping
  public List<StudentEntity> getAllStudents() {
	  return this.studentService.getAllStudents();
  }

  @GetMapping("/{id}")
  public StudentEntity getStudentById(@PathVariable("id") String id) {
	  return this.studentService.getStudentById(id);
  }

  @GetMapping("/first-name/{firstName}")
  public List<StudentDto> getStudentsByFirstName(@PathVariable("firstName") String firstName) {
	  return this.studentService.getStudentsByFirstName(firstName);
  }

  @PostMapping
  public StudentEntity createStudent(@RequestBody StudentDto student) {
	  return this.studentService.createStudent(student);
  }

  @PutMapping("/{id}")
  public StudentEntity updateStudent(@PathVariable("id") String id, @RequestBody StudentEntity student) {
	  return this.studentService.updateStudent(id, student);
  }

  @DeleteMapping("/{id}")
  public void deleteStudent(@PathVariable("id") String id) {
      this.studentService.deleteStudent(id);
  }

  @DeleteMapping
  public void deleteAllStudents() {
	  this.studentService.deleteAllStudents();
  }

  @GetMapping("/active")
  public List<StudentEntity> findByIsActive() {
	  return this.studentService.findByIsActive();
  }

}
