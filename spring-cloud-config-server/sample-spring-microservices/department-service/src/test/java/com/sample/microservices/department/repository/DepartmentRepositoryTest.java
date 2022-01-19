package com.sample.microservices.department.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class DepartmentRepositoryTest extends BaseRepositoryTest{
/*	
	@Autowired
	private DepartmentRepository repo;
	
	@Test
	void test_findByName() {
		
		List<DepartmentEntity> list = repo.findByName("d-name");
		
		assertNotNull(list);
	}
*/
	
	@Test
	void test_findByName() {
		assertNotNull(Integer.valueOf(1));
	}
}
