package com.sample.microservices.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.model.UserInfoStore;
import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.employee.enums.ManagerSortType;
import com.sample.microservices.employee.map.ManagerMapper;
import com.sample.microservices.employee.pagination.PageLayout;
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
	public PageLayout<Manager> getAllManagersWithPaginationAndFilter(List<String> names, 
			int pageNum, int pageSize, List<ManagerSortType> sort, Direction direction) {
		
		String[] sortStr = sort.stream().map(ManagerSortType::getValue).toArray(String[]::new);
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by(direction, sortStr));
		
		Page<ManagerEntity> entities = (names != null) ? this.repository.findByNameIn(names, pageable) :
														 this.repository.findAll(pageable);
		
		return getPage(pageNum, pageSize, entities);
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

	private PageLayout<Manager> getPage(int pageNum, int pageSize, Page<ManagerEntity> entities) {
		
		final List<Manager> list = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(entities.getContent())) {
			entities.stream().forEach(e-> {
				
				//may do extra stuff				
				list.add(this.mapper.entityToManager(e));				
				
			});
		}
		
		final PageLayout<Manager> page = new PageLayout<>();
		page.setTotalElements(entities.getTotalElements());
		page.setTotalPages(entities.getTotalPages());
		
		int firstElementNum = pageSize * pageNum - pageSize;
		page.setFirstElementNum(firstElementNum < 0 ? 0 : firstElementNum + 1);
		page.setData(list);
				
		return page;
		
	}

}
