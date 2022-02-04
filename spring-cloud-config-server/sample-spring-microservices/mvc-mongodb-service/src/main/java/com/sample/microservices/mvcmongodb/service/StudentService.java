package com.sample.microservices.mvcmongodb.service;

import java.util.List;

import com.sample.microservices.mvcmongodb.model.StudentEntity;
import com.sample.microservices.mvcmongodb.model.dto.Student;

public interface StudentService {

	List<StudentEntity> getAllStudents();
	List<StudentEntity> getStudentsByFirstName(final String firstName);
	StudentEntity getStudentById(final String id);
	StudentEntity createStudent(final Student student);
	StudentEntity updateStudent(final String id, final StudentEntity student);
	void deleteStudent(final String id);
	void deleteAllStudents();
	List<StudentEntity> findByIsActive();
	
}
