package org.application.configuration;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ProducerConfiguration {
    @Value("${spring.kafka.bootstrap-server}")
    private String bootstrapServer;
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        Map<String, Object> producerConfigurations = getProducerConfiguration();
        return new DefaultKafkaProducerFactory<>(producerConfigurations);
    }

    private Map<String, Object> getProducerConfiguration() {
        Map<String, Object> producerConfigurations = new HashMap<>();
        producerConfigurations.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapServer);
        producerConfigurations.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerConfigurations.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return producerConfigurations;
    }


    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        System.out.println("**********************");
        return new KafkaTemplate<>(producerFactory());
    }

}
