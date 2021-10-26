package com.sample.microservices.employee.service;

import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.model.UserInfoStore;
import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.employee.map.ManagerMapper;
import com.sample.microservices.employee.repository.ManagerEntityRepository;
import com.sample.microservices.model.dto.ManagerDto;

@Service
public class ManagerServiceImpl implements ManagerService {
	
	private final ManagerMapper mapper;
	private final ManagerEntityRepository repository;
    private final UserInfoStore userInfoStore;
    private final Environment environment;
    
	
	ManagerServiceImpl(ManagerMapper mapper, ManagerEntityRepository repository, 
			UserInfoStore userInfoStore, final Environment environment) {
		this.mapper = Mappers.getMapper(ManagerMapper.class);
		this.repository = repository;
    	this.userInfoStore = userInfoStore;
    	this.environment = environment;
	}
	
	@Override
	public Manager getManagerById(final Long id) {
		
		System.out.println("getAllManagers by " + this.userInfoStore.getUserName());
		
		String[] activeProfiles = this.environment.getActiveProfiles();
		
		for(String ap:activeProfiles) {
			System.out.println("activate profile: " + ap);			
		}
		
		return this.mapper.entityToManager(this.repository.findById(id).get());
	}

	@Override
	@Cacheable("all-managers")
	public List<Manager> getAllManagers() {
		
		List<ManagerEntity> entities = this.repository.findAll();
		
		System.out.println("getAllManagers by " + this.userInfoStore.getUserName());
		
		return this.mapper.entityToManager(entities);
	}

	@Override
	@Transactional
	public Manager createManager(final ManagerDto managerDto) {
		ManagerEntity entity = this.mapper.managerDtoToEntity(managerDto);
		entity.setId(null);
		
		entity.setName(this.userInfoStore.getUserName());
		
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
