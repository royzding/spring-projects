package com.sample.microservices.employee.service;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.model.UserInfoStore;
import com.sample.microservices.common.pagination.PageLayout;
import com.sample.microservices.common.util.UtilFuns;
import com.sample.microservices.employee.data.model.ManagerEntity;
import com.sample.microservices.employee.enums.ManagerSortType;
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
	public PageLayout<Manager> getAllManagersWithPaginationAndContaining(String name, int pageNum, int pageSize, 
			List<ManagerSortType> sort, Direction direction) {
		
		String[] sortStr = sort.stream().map(ManagerSortType::getValue).toArray(String[]::new);
		
		List<ManagerEntity> entities = (name != null && !name.trim().isEmpty()) ? 
										this.repository.findByNameContainingIgnoreCase(name, Sort.by(direction, sortStr)) :
										this.repository.findAll(Sort.by(direction, sortStr));
		
		List<Manager> list = this.mapper.entityToManager(entities);
		
		return PageLayout.getPageFromList(list, pageNum, pageSize, sort, direction);
	}

	@Override
	public PageLayout<Manager> getAllManagersWithPaginationAndFilter(List<String> names, 
			int pageNum, int pageSize, List<ManagerSortType> sort, Direction direction) {
		
		String[] sortStr = sort.stream().map(ManagerSortType::getValue).toArray(String[]::new);
		
		Pageable pageable = PageRequest.of(pageNum-1, pageSize, Sort.by(direction, sortStr));
		
		Page<ManagerEntity> entities = (names != null) ? this.repository.findByNameIn(names, pageable) :
														 this.repository.findAll(pageable);
		
		final List<Manager> list = new ArrayList<>();
		
		if(!CollectionUtils.isEmpty(entities.getContent())) {
			entities.stream().forEach(e-> {
				
				//may do extra stuff				
				list.add(this.mapper.entityToManager(e));				
				
			});
		}

		return PageLayout.getPage(pageNum, pageSize, entities, list);
	}

	@Override
	@Transactional
	public Manager createManager(final ManagerDto managerDto) {
		ManagerEntity entity = this.mapper.managerDtoToEntity(managerDto);
		entity.setId(null);
		
		//entity.setName(this.userInfoStore.getUserName());
		
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

	@Override
	public String getManagerMaxSalary(String name) {
		
		//String s1 = "\ts1:" + this.repository.get_salary_by_name(name);
		//String s2 = "\ts2:" + this.repository.getSalaryByName(name);
		//String s3 = "\ts3:" + this.repository.getSalaryByPName(name);
		//String s4 = "\ts4:" + this.repository.getSalaryByVName(name);
		
		Double salary2 = this.repository.getSalaryByName(name);
		
		
		return "s2:" + salary2;
	}

	@Override
	public void insertManagerBk(Long id) {
		
		this.repository.insertManagerBk(id);		
	}

	@Override
	public void zeroInParamPr() {
		
		try {
			
			this.repository.zeroInParamPr();		
			
		} catch (Exception e) {
			System.out.println("&&&&&&&&&&&&&&" + e.getMessage());
		}
	}

	@Override
	public void twoInParamPr(Long id, Double salaryInc) {
		this.repository.twoInParamPr(id, salaryInc);		
	}

	@Override
	public String getSalaryById(Long id) {
		return ""+this.repository.getSalaryById(id);
	}
	
	@Override
    public List<Manager> getManagersByName1(String name) {
    	return this.mapper.entityToManager(this.repository.getManagersByName1(name));
    }
	
	@Override
    public List<Manager> getManagersByName2(String name) {
    	return this.mapper.entityToManager(this.repository.getManagersByName2(name));
    }
	
	@Override
    public List<Manager> getManagersByName3(String name) {
    	return this.repository.getManagersByName3(name);
    }
	
	@Override
    public List<Manager> getManagersByNameIn1(List<String> names) {
    	return this.mapper.entityToManager(this.repository.getManagersByNameIn1(names));
    }
	
	@Override
    public List<Manager> getManagersByNameIn2(List<String> names) {
    	return this.mapper.entityToManager(this.repository.getManagersByNameIn2(names));
    }
	

}
