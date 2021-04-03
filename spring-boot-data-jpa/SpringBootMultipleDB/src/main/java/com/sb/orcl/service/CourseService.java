package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Course;
import com.sb.orcl.repo.CourseRepo;

@Service
public class CourseService {
	
	@Autowired
	CourseRepo courseRepo;
	
	public Course save(Course course) {
		return courseRepo.save(course);
	}
	
	public List<Course> findAll() {
		
		return courseRepo.findAll();
	}
	
	public Course findById(long id) {
		return courseRepo.findById(id).get();
	}
	
	public void delete(Course course) {
		courseRepo.delete(course);
	}
	
	public Course update(Course course) {
		return courseRepo.save(course);
	}

}
