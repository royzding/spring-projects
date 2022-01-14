package com.sample.microservices.department.repository;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

//@DataJpaTest
@DirtiesContext
@javax.transaction.Transactional
abstract class BaseRepositoryTest {

}
