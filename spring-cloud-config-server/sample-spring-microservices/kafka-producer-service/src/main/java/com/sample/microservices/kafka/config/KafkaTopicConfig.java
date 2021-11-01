package com.sample.microservices.kafka.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

@Configuration
public class KafkaTopicConfig {
    
    @Value(value = "${spring.kafka.bootstrap-servers}")
    private String bootstrapAddress;

    @Value(value = "${spring.kafka.topic01-name}")
    private String topic01Name;

    @Value(value = "${spring.kafka.topic02-name}")
    private String topic02Name;

    @Value(value = "${spring.kafka.topic03-name}")
    private String topic03Name;

    @Value(value = "${spring.kafka.topic04-name}")
    private String topic04Name;

    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }
    
    @Bean
    public NewTopic topic01() {
         return new NewTopic(topic01Name, 1, (short) 1);
    }
    
    @Bean
    public NewTopic topic02() {
         return new NewTopic(topic02Name, 2, (short) 1);
    }
    
    @Bean
    public NewTopic topic03() {
         return new NewTopic(topic03Name, 3, (short) 1);
    }
    
    @Bean
    public NewTopic topic04() {
         return TopicBuilder.name(topic04Name)
        		 .partitions(4)
        		 .replicas(1)
        		 .build();
    }
    
}