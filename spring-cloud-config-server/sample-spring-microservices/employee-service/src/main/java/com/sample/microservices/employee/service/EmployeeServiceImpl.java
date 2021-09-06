package com.sample.microservices.employee.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.model.Employee;
import com.sample.microservices.employee.data.model.EmployeeEntity;
import com.sample.microservices.employee.department.DepartmentService;
import com.sample.microservices.employee.map.EmployeeMapper;
import com.sample.microservices.employee.repository.EmployeeEntiyRepository;
import com.sample.microservices.model.dto.EmployeeDto;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	private final EmployeeMapper mapper;
	private final EmployeeEntiyRepository repository;
	private final DepartmentService departmentService;
	
	EmployeeServiceImpl(EmployeeMapper mapper, EmployeeEntiyRepository repository, DepartmentService departmentService) {
		this.mapper = Mappers.getMapper(EmployeeMapper.class);
		this.repository = repository;
		this.departmentService = departmentService;
	}
	
	@Override
	public Employee getEmployeeById(final Long id) {
		Employee employee = this.mapper.entityToEmployee(this.repository.findById(id).get());
		employee.setDepName(this.departmentService.getDepartmentMap().get(employee.getDepId()).getName());
		return employee;
	}

	@Override
	@Cacheable("all-employees")
	public List<Employee> getAllEmployees() {
		
		List<EmployeeEntity> entities = this.repository.findAll();
		
		List<Employee> employees = this.mapper.entityToEmployee(entities);
		
		employees.forEach(e->{
			e.setDepName(this.departmentService.getDepartmentMap().get(e.getDepId()).getName());
		});
				
		return employees;
	}

	@Override
	public Employee createEmployee(final EmployeeDto employeeDto) {
		EmployeeEntity entity = this.mapper.employeeDtoToEntity(employeeDto);
		entity.setId(null);
		
		this.repository.save(entity);
		
		return this.mapper.entityToEmployee(entity);
	}

	@Override
	public List<Employee> createEmployees(List<EmployeeDto> employeeDtos) {

		List<EmployeeEntity> entities = this.mapper.employeeDtoToEntity(employeeDtos);
		
		entities = this.repository.saveAll(entities);

		return this.mapper.entityToEmployee(entities);
	}
	
	@Override
	public void deleteEmployeeById(final Long id) {
        this.repository.deleteById(id);
	}

	@Override
	public void deleteAllEmployees() {
        this.repository.deleteAll();;
	}

	@Override
	public void updateEmployee(final Long id, final EmployeeDto employee) throws Exception {
		
		EmployeeEntity entity = this.repository.findById(id).orElseThrow(
				()-> new Exception("Employee not found with the given id:" + id));
		
		entity = this.mapper.employeeDtoToEntity(employee);
		
		this.repository.save(entity);
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

	@Override
	public List<Employee> getEmployeesByDepartmentId(Long id) {		
		
		List<Employee> employees = this.mapper.entityToEmployee(this.repository.findByDepId(id));
		
		employees.forEach(e->{
			e.setDepName(this.departmentService.getDepartmentMap().get(id).getName());
		});
		
		return employees;
	}

}
