package com.sample.microservices.webfluxmongodb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.dto.StudentDto;
import com.sample.microservices.webfluxmongodb.service.StudentServiceImpl;

import io.swagger.v3.oas.annotations.tags.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/student")
@Tag(name = "Student APIs", description = "APIs for Student CRUD operations")
public class StudentController {
    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @GetMapping("/last/{name}")
    @ResponseBody
    public Flux<StudentEntity> findByLastName(@PathVariable("name") String name) {
        return studentServiceImpl.findByLastName(name);
    }

    @GetMapping("/first-contains/{name}")
    @ResponseBody
    public Flux<StudentEntity> findByFirstNameContains(@PathVariable("name") String name) {
        return studentServiceImpl.findByFirstNameContains(name);
    }

    @GetMapping("/all-entities")
    @ResponseBody
    public Flux<StudentEntity> findAllStudentEntity() {
        return studentServiceImpl.findAllStudentEntity();
    }

    @GetMapping("/all-dtos")
    @ResponseBody
    public Flux<StudentDto> findAll() {
        return studentServiceImpl.findAllStudent();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<StudentDto>> findStudentById(@PathVariable("id") String id) {
        Mono<StudentDto> student = studentServiceImpl.findByStudentId(id);
        return new ResponseEntity<Mono<StudentDto>>(student, student != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createStudent(@RequestBody StudentDto student) {
        studentServiceImpl.createStudent(student);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<StudentDto> update(@PathVariable("id") String id, @RequestBody StudentDto student) {
        return studentServiceImpl.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id) {
        studentServiceImpl.deleteStudent(id).subscribe();
    }

}
