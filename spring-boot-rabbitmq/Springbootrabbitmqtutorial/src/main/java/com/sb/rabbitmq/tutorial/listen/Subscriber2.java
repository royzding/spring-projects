package com.sb.rabbitmq.tutorial.listen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sb.rabbitmq.tutorial.model.Student;

@Component
public class Subscriber2 {

	private static final Logger LOGGER = LoggerFactory.getLogger(Subscriber2.class);

	@RabbitListener(queues = "#{queue.getName()}")	// Dynamically reading the queue name using SpEL from the "queue" object.
	public void receive(final Object student) {
		LOGGER.info("Subscriber2 Listening messages from the queue!!");
		LOGGER.info("Subscriber2 Received the following message from the queue= " + student);
		LOGGER.info("Subscriber2 Message received successfully from the queue.");
	}
}
