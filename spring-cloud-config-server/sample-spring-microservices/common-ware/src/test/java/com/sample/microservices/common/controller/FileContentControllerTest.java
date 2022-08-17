package com.sample.microservices.common.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;

@SpringBootTest(classes=FileContentController.class)
public class FileContentControllerTest {

	@Autowired
	private FileContentController fileContentController;
	
	@Test
	void test_getFileInfo() throws IOException {
		
		assertEquals("{\"file\":\"test\"}", fileContentController.getFileInfo());
	}
}
