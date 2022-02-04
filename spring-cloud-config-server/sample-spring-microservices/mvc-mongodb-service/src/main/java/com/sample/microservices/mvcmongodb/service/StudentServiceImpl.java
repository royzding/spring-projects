package com.sample.microservices.mvcmongodb.service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sample.microservices.mvcmongodb.model.StudentEntity;
import com.sample.microservices.mvcmongodb.model.dto.Student;
import com.sample.microservices.mvcmongodb.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	  private final StudentRepository studentRepository;
	  
	  StudentServiceImpl(StudentRepository studentRepository) {
		  this.studentRepository = studentRepository;		  
	  }

	  @Override
	  public List<StudentEntity> getAllStudents() {
	       return studentRepository.findAll();
	  }

	  @Override
	  public List<StudentEntity> getStudentsByFirstName(final String firstName) {
		  
	      return studentRepository.findByFirstName(firstName);
	  }

	  @Override
	  public StudentEntity getStudentById(final String id) {
		  Optional<StudentEntity> tut = studentRepository.findById(id);
	      return !tut.isEmpty() ? tut.get() : null;
	  }

	  @Override
	  public StudentEntity createStudent(final Student student) {
	      StudentEntity nStudent = new StudentEntity();
	      nStudent.setFirstName(student.getFirstName());
	      nStudent.setLastName(student.getLastName());
	      nStudent.setActive(student.isActive());
	      nStudent.setCourses(student.getCourses());
	      nStudent.setCreated(LocalTime.now());
	      nStudent.setGender(student.getGender());
	      	      
	      return studentRepository.save(nStudent);

	  }

	  @Override
	  public StudentEntity updateStudent(final String id, final StudentEntity student) {
		  Optional<StudentEntity> studentData = studentRepository.findById(id);

	      StudentEntity uStudent = studentData.get();
	      uStudent.setFirstName(student.getFirstName());
	      uStudent.setLastName(student.getLastName());
	      uStudent.setActive(student.isActive());
	      
	      return studentRepository.save(uStudent);
	  }

	  @Override
	  public void deleteStudent(final String id) {
	      studentRepository.deleteById(id);
	  }

	  @Override
	  public void deleteAllStudents() {		  
		  studentRepository.deleteAll();
	  }

	  @Override
	  public List<StudentEntity> findByIsActive() {
	    return studentRepository.findByIsActive(true);
	  }

}
