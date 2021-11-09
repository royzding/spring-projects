package com.sample.microservices.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyStepExecutionListener implements StepExecutionListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyStepExecutionListener.class);

	@Override
	public void beforeStep(StepExecution stepExecution) {
		System.out.println("##################### StepExecutionListener - beforeStep");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		System.out.println("@@@@@@@@@@@@@@@@@@@@@ StepExecutionListener - afterStep");
		return null;
	}

}