package com.sample.microservices.employee.service;

import java.util.List;

import com.sample.microservices.employee.model.Manager;
import com.sample.microservices.model.dto.ManagerDto;

public interface ManagerService {

    Manager getManagerById(final Long id);
    
    List<Manager> getAllManagers();
       
    Manager createManager(final ManagerDto managerDto);
    
    List<Manager> createManagers(final List<ManagerDto> managerDto);
    
    void deleteManagerById(final Long id);
    
    void deleteAllManagers();
    
    void updateManager(final Long id, final ManagerDto managerDto)  throws Exception;
    
}
