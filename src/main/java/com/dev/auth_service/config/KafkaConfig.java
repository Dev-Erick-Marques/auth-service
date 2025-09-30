package com.dev.auth_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class KafkaConfig {
    @Value("${kafka.topics}")
    private String[] topics;

    @Bean
    public List<NewTopic> createTopics(){
        return Arrays.stream(topics)
                .map(t -> new NewTopic(t, 1, (short)1))
                .toList();
    }
}
