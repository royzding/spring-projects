package com.test.rabbitmq.consumer;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.test.rabbitmq.consumer.model.Payload;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqConsumerApplicationTests {

    @Test
    public void contextLoads() {
    	
    	Payload payload = new Payload();
    	
    	when(payload.getMessage1()).thenReturn("this is m1");
    	
    	
    	
    }

}
