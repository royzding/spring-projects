package com.sample.microservices.department.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.department.data.model.DepartmentEntity;
import com.sample.microservices.department.map.DepartmentMapper;
import com.sample.microservices.department.model.Department;
import com.sample.microservices.department.model.dto.DepartmentDto;
import com.sample.microservices.department.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService {
	
	private final DepartmentMapper mapper;
	private final DepartmentRepository repository;
	
	DepartmentServiceImpl(DepartmentMapper mapper, DepartmentRepository repository) {
		this.mapper = Mappers.getMapper(DepartmentMapper.class);
		this.repository = repository;
	}
	
	@Override
	public Department getDepartmentById(final Long id) {
		return this.mapper.entityToDepartment(this.repository.findById(id).get());
	}

	@Override
	@Cacheable("all-departments")
	public List<Department> getAllDepartments() {
		
		List<DepartmentEntity> entities = this.repository.findAll();
		
		
		return this.mapper.departmentEntityToDepartment(entities);
	}

	@Override
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

		return this.mapper.departmentEntityToDepartment(entities);
	}

	@Override
	public void updateDepartment(Long id, DepartmentDto departmentDto) throws Exception {
		
		DepartmentEntity entity = this.repository.findById(id).orElseThrow(
				()-> new Exception("Department not found with the given id:" + id));
		
		entity = this.mapper.departmentDtoToEntity(departmentDto);
		
		this.repository.save(entity);
	}
	
}
