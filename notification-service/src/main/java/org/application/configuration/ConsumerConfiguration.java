package org.application.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfiguration {

    @Value("${spring.kafka.bootstrap-server}")
    private String bootstrapServer;


    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        Map<String, Object> consumerConfigurations = getConsumerConfiguration();
        return new DefaultKafkaConsumerFactory<>(consumerConfigurations);
    }

    private Map<String, Object> getConsumerConfiguration() {
        Map<String, Object> consumerConfigurations = new HashMap<>();
        consumerConfigurations.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        consumerConfigurations.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerConfigurations.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return consumerConfigurations;
    }


    @Bean
    public KafkaListenerContainerFactory<?> kafkaListenerContainerFactory(ConsumerFactory<String, Object> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory concurrentKafkaListenerContainerFactory=new ConcurrentKafkaListenerContainerFactory<>();
        concurrentKafkaListenerContainerFactory.setConsumerFactory(consumerFactory);
        return concurrentKafkaListenerContainerFactory;
    }


}
