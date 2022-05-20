package com.sample.microservices.uploadfiles;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.sample.microservices.uploadfiles.config.UploadFilesTestConfiguration;

@ActiveProfiles({"unit","api-security","user-info"})
@SpringBootTest(webEnvironment=WebEnvironment.NONE, classes = UploadFilesTestConfiguration.class)
@AutoConfigureTestDatabase
@Transactional
public abstract class BaseTest {
}
