package com.sample.microservices.department.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sample.microservices.department.data.model.HolidayDateEntity;

@Repository
public interface HolidayDateRepository extends JpaRepository<HolidayDateEntity, Long>{
	
	List<HolidayDateEntity> findByName(String name);
	List<HolidayDateEntity> findByNameIn(List<String> name);
	Optional<HolidayDateEntity> findByNameAndDayAndMonthAndYear(String name, Integer day, Integer Month, Integer year); 

	@Query(value = "SELECT * FROM Holiday_Date h WHERE h.name=:name", nativeQuery = true)
	List<HolidayDateEntity> getHolidayDateByName(String name);
	
}

