package com.sb.post.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sb.post.model.City;
import com.sb.post.repo.CityRepo;

@Service
public class CityService {
	
	@Autowired
	CityRepo cityRepo;
	
	@Transactional
	public City save(City city) {
		return cityRepo.save(city);
	}
	
	public List<City> findAll() {
		
		return cityRepo.findAll();
	}
	
	public City findById(long id) {
		return cityRepo.findById(id).get();
	}
	
	public void delete(City city) {
		cityRepo.delete(city);
	}
	
	@Transactional
	public City update(City city) {
		return cityRepo.save(city);
	}

}
