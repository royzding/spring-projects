package com.sample.microservices.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sample.microservices.employee.data.model.EmployeeEntity;

@Repository
public interface EmployeeEntiyRepository extends JpaRepository<EmployeeEntity, Long>{
	
	List<EmployeeEntity> findByName(String name);
	List<EmployeeEntity> findByNameIn(List<String> name);

	List<EmployeeEntity> findByDepId(Long depId);
}
