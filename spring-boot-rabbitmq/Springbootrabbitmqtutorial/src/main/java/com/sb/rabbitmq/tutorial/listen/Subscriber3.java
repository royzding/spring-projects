package com.sb.rabbitmq.tutorial.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Subscriber3 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber3.class);

	public void receiveMessage(final Object student) {
		LOGGER.info("Subscriber3 Received <" + student + ">");
	}
	  
}
