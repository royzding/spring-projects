package com.sample.microservices.mvcmongodb.service;

import java.util.List;

import com.sample.microservices.common.data.model.StudentEntity;
import com.sample.microservices.common.model.dto.StudentDto;

public interface StudentService {

	List<StudentEntity> getAllStudents();
	List<StudentDto> getStudentsByFirstName(final String firstName);
	StudentEntity getStudentById(final String id);
	StudentEntity createStudent(final StudentDto student);
	StudentEntity updateStudent(final String id, final StudentEntity student);
	void deleteStudent(final String id);
	void deleteAllStudents();
	List<StudentEntity> findByIsActive();
	
}
