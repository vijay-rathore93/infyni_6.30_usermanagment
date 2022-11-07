package org.application.configuration;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("user_management_topic").replicas(10).partitions(10).build();
    }
}
