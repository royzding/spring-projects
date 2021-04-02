package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Student;
import com.sb.orcl.repo.StudentRepo;

@Service
public class StudentService {
	
	@Autowired
	StudentRepo studentRepo;
	
	public Student save(Student student) {
		return studentRepo.save(student);
	}
	
	public List<Student> findAll() {
		
		return studentRepo.findAll();
	}
	
	public Student findById(long id) {
		return studentRepo.findById(id).get();
	}
	
	public void delete(Student student) {
		studentRepo.delete(student);
	}
	
	public Student update(Student student) {
		return studentRepo.save(student);
	}

}
