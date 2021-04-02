package com.sb.orcl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long>{
	
	List<Course> findByNameContaining(String name);
	
}
