package com.async.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.async.test.data.model.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
	List<EmployeeEntity> findByFirstName(String firstName);
	List<EmployeeEntity> findByFirstNameAndLastName(String firstName, String lastName);
	List<EmployeeEntity> findByFirstNameIn(List<String> firstName);

}
