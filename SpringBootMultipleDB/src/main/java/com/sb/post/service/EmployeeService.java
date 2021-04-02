package com.sb.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sb.post.model.Employee;
import com.sb.post.repo.EmployeeRepo;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@Autowired
	PlatformTransactionManager transactionManager;
	
	public Employee save(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> findAll() {
		
		return employeeRepo.findAll();
	}
	
	public Employee findById(long id) {
		return employeeRepo.findById(id).get();
	}
	
	public void delete(Employee employee) {
		employeeRepo.delete(employee);
	}
	
	public Employee update(Employee employee) {
		
		TransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		try {
			
			//do something
			transactionManager.commit(status);
			
		} catch(DataAccessException e) {
	         System.out.println("Error in creating record, rolling back");
	         transactionManager.rollback(status);
	         throw e;
	    }
		
		return employeeRepo.save(employee);
	}

}
