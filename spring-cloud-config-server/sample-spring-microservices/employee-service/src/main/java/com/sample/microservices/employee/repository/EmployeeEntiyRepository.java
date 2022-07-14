package com.sample.microservices.employee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.microservices.common.model.EmployeeInfo;
import com.sample.microservices.common.model.Manager;
import com.sample.microservices.employee.data.model.EmployeeEntity;

@Repository
public interface EmployeeEntiyRepository extends JpaRepository<EmployeeEntity, Long>{
	
	List<EmployeeEntity> findByName(String name);
	List<EmployeeEntity> findByNameIn(List<String> name);

	List<EmployeeEntity> findByDepId(Long depId);
	
    @Query("SELECT new com.sample.microservices.common.model.EmployeeInfo(ee.id, ee.name, ee.salary, me.name) "
    		+ " FROM EmployeeEntity ee  "
    		+ " inner join ManagerEntity me "
    		+ " on ee.managerEntity.id = me.id and me.id = :mId ")
    List<EmployeeInfo> getEmployeesByManagerId(@Param("mId") Long mId);
	
    @Query("SELECT new com.sample.microservices.common.model.EmployeeInfo(ee.id, ee.name, ee.salary, de.name, me.name) "
    		+ " FROM EmployeeEntity ee  "
    		+ " inner join ManagerEntity me "
    		+ " on ee.managerEntity.id = me.id and me.id = :mId "
    		+ " inner join DepartmentEntity de "
    		+ " on ee.depId = de.id and de.id = :dId ")
    List<EmployeeInfo> getEmployeesByManagerIdAndDeptId(@Param("mId") Long mId, @Param("dId") Long dId);
	
}
