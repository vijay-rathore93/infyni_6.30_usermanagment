package org.application.consumer;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class NotificationConsumer {

    @KafkaListener(topics = "user_management_topic",groupId = "groupid")
    public void consumerNotification(ConsumerRecord<String,Object>  consumedRecord){
        System.out.println(consumedRecord);
    }

}
