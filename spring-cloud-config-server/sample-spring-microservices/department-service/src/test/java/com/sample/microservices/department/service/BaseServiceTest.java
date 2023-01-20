package com.sample.microservices.department.service;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles({"unit", "api-security", "user-info"})
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@AutoConfigureTestDatabase
@Transactional
public abstract class BaseServiceTest {

}
