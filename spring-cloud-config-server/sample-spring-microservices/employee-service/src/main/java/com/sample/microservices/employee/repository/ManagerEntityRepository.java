package com.sample.microservices.employee.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.microservices.employee.data.model.ManagerEntity;

@Repository
public interface ManagerEntityRepository extends JpaRepository<ManagerEntity, Long>{
	
	List<ManagerEntity> findByName(String name);
	List<ManagerEntity> findByNameIn(List<String> names);
	List<ManagerEntity> findByNameContainingIgnoreCase(String name, Sort sort);
	
	Page<ManagerEntity> findByNameIn(List<String> names, Pageable pageable);
	
    @Procedure(procedureName = "get_salary_by_name", outputParameterName = "out_salary")
    Double getSalaryByName(@Param("in_name") String name);
    
    @Procedure(procedureName = "zero_in_param_pr")
    void zeroInParamPr();
	
    @Procedure(procedureName = "insert_manager_bk")
    void insertManagerBk(@Param("in_manager_id") Long id);
	
    @Procedure(procedureName = "two_in_param_pr")
    void twoInParamPr(@Param("in_manager_id") Long id, @Param("in_salary_inc") Double salaryInc);
    
    //calling an oracle function get_salary_by_id(in_id IN NUMBER)
    @Query(nativeQuery = true, value = "SELECT get_salary_by_id(:id) FROM dual")
    Double getSalaryById(@Param("id") Long id);
}
