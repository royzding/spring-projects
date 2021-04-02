package com.sb.orcl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sb.orcl.model.Test;
import com.sb.orcl.repo.TestRepo;

@Service
public class TestService {
	
	@Autowired
	TestRepo testRepo;
	
	public Test save(Test test) {
		return testRepo.save(test);
	}
	
	public List<Test> findAll() {
		
		return testRepo.findAll();
	}
	
	public Test findById(long id) {
		return testRepo.findById(id).get();
	}
	
	public void delete(Test test) {
		testRepo.delete(test);
	}
	
	public Test update(Test test) {
		return testRepo.save(test);
	}

}
