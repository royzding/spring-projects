package com.sample.microservices.employee.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.sample.microservices.common.model.Manager;
import com.sample.microservices.common.pagination.PageLayout;
import com.sample.microservices.employee.enums.ManagerSortType;
import com.sample.microservices.model.dto.ManagerDto;

public interface ManagerService {

    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
    
    PageLayout<Manager> getAllManagersWithPaginationAndContaining(String name, int pageNum, int pageSize, 
			List<ManagerSortType> sort, Direction direction);
    
    PageLayout<Manager> getAllManagersWithPaginationAndFilter(List<String> names, 
    		int pageNum, int pageSize, List<ManagerSortType> sort, Sort.Direction direction);
       
    Manager createManager(final ManagerDto managerDto);
    
    List<Manager> createManagers(final List<ManagerDto> managerDto);
    
    void deleteManagerById(final Long id);
    
    void deleteAllManagers();
    
    void updateManager(final Long id, final ManagerDto managerDto)  throws Exception;
    
}
