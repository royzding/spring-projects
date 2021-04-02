package com.sb.post.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.sb.post.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>, QueryByExampleExecutor<Employee> {

}
