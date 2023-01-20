package com.sample.microservices.department.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import com.sample.microservices.common.annotation.EventType;
import com.sample.microservices.common.annotation.Loggable;
import com.sample.microservices.common.annotation.Loggable.Level;
import com.sample.microservices.common.annotation.LoggableEvents;
import com.sample.microservices.common.annotation.LoggableType;
import com.sample.microservices.common.model.Department;
import com.sample.microservices.common.model.Employee;
import com.sample.microservices.common.util.UtilFuns;
import com.sample.microservices.department.data.model.DepartmentEntity;
import com.sample.microservices.department.employee.EmployeeService;
import com.sample.microservices.department.map.DepartmentMapper;
import com.sample.microservices.department.model.dto.DepartmentDto;
import com.sample.microservices.department.repository.DepartmentRepository;

@Service
@LoggableType
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentMapper mapper;
	private final DepartmentRepository repository;
	private final EmployeeService employeeService;
		
	public DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository, EmployeeService employeeService) {
		this.mapper = Mappers.getMapper(DepartmentMapper.class);
		this.repository = repository;
		this.employeeService = employeeService;
	}
	
	@Override
	public Department getDepartmentById(final Long id) {
		
		return this.mapper.entityToDepartment(this.repository.findById(id).get());
	}

	@Override
	public List<Department> getDeptsByName(final String name) {
		
		return this.mapper.entityToDepartment(this.repository.getDeptsByName(name));
	}

	@Override
	@Cacheable("all-departments")
	@Loggable()
	@LoggableEvents(type=EventType.READ)
	public List<Department> getAllDepartments() {
		
		List<DepartmentEntity> entities = this.repository.findAll();
		
		
		return this.mapper.entityToDepartment(entities);
	}

	@Override
	@LoggableEvents(type=EventType.DELETE)
	public void deleteDepartmentById(final Long id) {
        this.repository.deleteById(id);
	}

	@Override
	public void deleteAllDepartments() {
        this.repository.deleteAll();;
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
	@Loggable(level=Level.WARN)
	@LoggableEvents(type=EventType.CREATE)
	@Transactional
	public Department createDepartment(DepartmentDto departmentDto) {
		DepartmentEntity entity = this.mapper.departmentDtoToEntity(departmentDto);
		entity.setId(null);
		
		this.repository.save(entity);
		
		return this.mapper.entityToDepartment(entity);
	}

	@Override
	public List<Department> createDepartments(List<DepartmentDto> departmentDtos) {

		List<DepartmentEntity> entities = this.mapper.departmentDtoToEntity(departmentDtos);
		
		entities = this.repository.saveAll(entities);

		return this.mapper.entityToDepartment(entities);
	}

	@Override
	@Loggable(level=Level.INFO)
	@LoggableEvents(type=EventType.UPDATE)
	public void updateDepartment(Long id, DepartmentDto departmentDto) throws Exception {
		
		DepartmentEntity entity = this.repository.findById(id).orElseThrow(
				()-> new Exception("Department not found with the given id:" + id));
		
		entity = this.mapper.departmentDtoToEntity(departmentDto);
		
		this.repository.save(entity);
	}

	@Override
	public List<Employee> getEmployeesByDepartmentId(Long id) {
		return this.employeeService.getEmployeesByDepartmentId(id);
	}
	
}
