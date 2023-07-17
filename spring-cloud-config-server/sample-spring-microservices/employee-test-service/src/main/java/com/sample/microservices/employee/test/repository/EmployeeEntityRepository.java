package com.sample.microservices.employee.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sample.microservices.employee.test.model.EmployeeEntity;

public interface EmployeeEntityRepository extends JpaRepository<EmployeeEntity, Long> {

}
