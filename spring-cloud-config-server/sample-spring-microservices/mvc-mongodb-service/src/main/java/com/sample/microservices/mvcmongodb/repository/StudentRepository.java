package com.sample.microservices.mvcmongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sample.microservices.mvcmongodb.model.StudentEntity;

public interface StudentRepository extends MongoRepository<StudentEntity, String> {
  List<StudentEntity> findByFirstName(String firstName);
  List<StudentEntity> findByIsActive(boolean isActive);
}
