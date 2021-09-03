package com.sample.microservices.asyncaop.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.asyncaop.data.model.EmployeeEntity;
import com.sample.microservices.asyncaop.map.EmployeeMapper;
import com.sample.microservices.asyncaop.model.Employee;
import com.sample.microservices.asyncaop.model.dto.EmployeeDto;
import com.sample.microservices.asyncaop.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	List<Employee> list = List.of(
			new Employee(1, "ef1", "el1", true), 
			new Employee(2, "ef2", "el2", true), 
			new Employee(3, "ef3", "el3", true)
			);

	private final EmployeeMapper mapper;
	private final EmployeeRepository repository;
	
	EmployeeServiceImpl(EmployeeMapper mapper, EmployeeRepository repository) {
		this.mapper = Mappers.getMapper(EmployeeMapper.class);
		this.repository = repository;
	}
	
	@Override
	@Cacheable("all-employees")
	public List<Employee> getAllEmployees() {
		
		List<EmployeeEntity> entities = this.repository.findAll();
		
		
		return this.mapper.employeeEntityToEmployee(entities);
	}

	@Override
	public Employee getEmployee(Long id) {
		return this.mapper.employeeEntityToEmployee(this.repository.findById(id).get());
	}

	@Override
	public Employee createEmployee(EmployeeDto employee) {
		Employee emp = new Employee();
		emp.setId(employee.getId() + 1L);
		emp.setFirstName(employee.getFirstName()+"-new");
		emp.setLastName(employee.getLastName()+"-new");
		return emp;
	}

	@Override
	public void deleteEmployee(Long id) {
        System.out.println("Method deleteEmployee() called");
	}

	@Override
	public void updateEmployee(Long id, EmployeeDto employee) {
        System.out.println("Method updateEmployee() called");
	}

	@Override
	@Cacheable("cacheable-time")
	public Long getCacheableTime() {
		
		StopWatch sw = new StopWatch();
		
		sw.start();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sw.stop();
		
		System.out.println("time taking in getCacheableTime:" + sw.getTotalTimeMillis());
		
		return sw.getTotalTimeMillis();
	}
}
