package com.sb.post.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sb.post.model.City;
	
@Repository
public interface CityRepo extends JpaRepository<City, Long>{

}
	