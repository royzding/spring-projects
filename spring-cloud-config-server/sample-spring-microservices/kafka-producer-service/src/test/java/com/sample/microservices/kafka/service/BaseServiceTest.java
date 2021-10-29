package com.sample.microservices.kafka.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("unit")
@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.NONE)
@RunWith(SpringRunner.class)
public abstract class BaseServiceTest {

}
