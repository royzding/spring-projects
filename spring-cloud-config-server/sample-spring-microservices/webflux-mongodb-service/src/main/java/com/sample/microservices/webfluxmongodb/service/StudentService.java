package com.sample.microservices.webfluxmongodb.service;

import java.util.List;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.dto.StudentDto;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
 
public interface StudentService
{
    void createStudent(StudentDto studentDto);
     
    Mono<StudentDto> findByStudentId(String id);
 
    Flux<StudentEntity> findByLastName(String lastName);
    
    Flux<StudentEntity> findByFirstNameContains(String firstName);
    
    Flux<StudentEntity> findAllStudentEntity();
    
    List<StudentEntity> findAllStudentEntityList();
    
    Flux<StudentDto> findAllStudent();
    
    Mono<StudentDto> updateStudent(String id, StudentDto studentDto);
 
    Mono<Void> deleteStudent(String id);
}