package com.sample.microservices.employee.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.employee.data.model.ManagerEntity;

@Repository
public interface ManagerEntityRepository extends JpaRepository<ManagerEntity, Long>{
	
	List<ManagerEntity> findByName(String name);
	List<ManagerEntity> findByNameIn(List<String> names);
	List<ManagerEntity> findByNameContainingIgnoreCase(String name, Sort sort);
	
	Page<ManagerEntity> findByNameIn(List<String> names, Pageable pageable);
	
}
