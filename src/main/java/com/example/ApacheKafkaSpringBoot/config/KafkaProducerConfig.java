package com.example.ApacheKafkaSpringBoot.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public NewTopic createNewTopic() {
        return new NewTopic("dev3", 4, (short) 1);
    }
}
