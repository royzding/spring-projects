package com.sb.orcl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long>{
	
	List<Student> findByNameContaining(String name);
	
}
