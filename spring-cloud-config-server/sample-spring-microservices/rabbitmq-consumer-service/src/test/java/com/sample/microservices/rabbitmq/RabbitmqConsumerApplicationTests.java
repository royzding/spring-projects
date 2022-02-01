package com.sample.microservices.rabbitmq;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.sample.microservices.rabbitmq.model.Payload;


@SpringBootTest
public class RabbitmqConsumerApplicationTests {

    @Test
    public void contextLoads() {
    	
    	Payload payload = new Payload();
    	
    	when(payload.getMessage1()).thenReturn("this is m1");
    	
    	
    	
    }

}
