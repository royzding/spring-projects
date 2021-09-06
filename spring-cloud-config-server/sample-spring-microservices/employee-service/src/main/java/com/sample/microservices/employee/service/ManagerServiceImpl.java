package com.sample.microservices.employee.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.employee.repository.ManagerEntityRepository;
import com.sample.microservices.employee.map.ManagerMapper;
import com.sample.microservices.employee.model.Manager;
import com.sample.microservices.model.dto.ManagerDto;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	private final ManagerMapper mapper;
	private final ManagerEntityRepository repository;
	
	ManagerServiceImpl(ManagerMapper mapper, ManagerEntityRepository repository) {
		this.mapper = Mappers.getMapper(ManagerMapper.class);
		this.repository = repository;
	}
	
	@Override
	public Manager getManagerById(final Long id) {
		return this.mapper.entityToManager(this.repository.findById(id).get());
	}

	@Override
	@Cacheable("all-managers")
	public List<Manager> getAllManagers() {
		
		List<ManagerEntity> entities = this.repository.findAll();
		
		
		return this.mapper.entityToManager(entities);
	}

	@Override
	public Manager createManager(final ManagerDto managerDto) {
		ManagerEntity entity = this.mapper.managerDtoToEntity(managerDto);
		entity.setId(null);
		
		this.repository.save(entity);
		
		return this.mapper.entityToManager(entity);
	}

	@Override
	public List<Manager> createManagers(List<ManagerDto> managerDtos) {

		List<ManagerEntity> entities = this.mapper.managerDtoToEntity(managerDtos);
		
		entities = this.repository.saveAll(entities);

		return this.mapper.entityToManager(entities);
	}
	
	@Override
	public void deleteManagerById(final Long id) {
        this.repository.deleteById(id);
	}

	@Override
	public void deleteAllManagers() {
        this.repository.deleteAll();;
	}

	@Override
	public void updateManager(final Long id, final ManagerDto manager) throws Exception {
		
		ManagerEntity entity = this.repository.findById(id).orElseThrow(
				()-> new Exception("Manager not found with the given id:" + id));
		
		entity = this.mapper.managerDtoToEntity(manager);
		
		this.repository.save(entity);
	}

}
