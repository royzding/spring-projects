package com.sb.orcl.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.orcl.model.Test;

@Repository
public interface TestRepo extends JpaRepository<Test, Long>{
	
	List<Test> findByNameContaining(String name);
	
}
