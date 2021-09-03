package com.sample.microservices.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.department.data.model.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{
	
	List<DepartmentEntity> findByName(String name);
	List<DepartmentEntity> findByNameIn(List<String> name);

}

