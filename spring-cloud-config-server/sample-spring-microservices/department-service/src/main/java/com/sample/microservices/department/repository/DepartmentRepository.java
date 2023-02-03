package com.sample.microservices.department.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sample.microservices.department.data.model.DepartmentEntity;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>{
	
	List<DepartmentEntity> findByName(String name);
	List<DepartmentEntity> findByNameIn(List<String> name);

	@Query(value = "SELECT * FROM Department d WHERE d.name=:name", nativeQuery = true)
	List<DepartmentEntity> getDeptsByName(String name);
	
    @Procedure(procedureName = "p_insert_two_param")
    void insertTwoParam(@Param("delta") Integer delta, @Param("description") String description);

    @Procedure(procedureName = "p_insert_two_param_plus_one")
    void pInsertTwoParamPlusOne();

    @Procedure(procedureName = "p_insert_two_param_minus_one")
    void pInsertTwoParamMinusOne();

}

