package com.sample.microservices.department.repository;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles({"unit", "api-security", "user-info"})
@ComponentScan({"com.sample.microservices.employee","com.sample.microservices.common"})
@SpringBootTest
@DirtiesContext
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@javax.transaction.Transactional
abstract class BaseRepositoryTest {

}
