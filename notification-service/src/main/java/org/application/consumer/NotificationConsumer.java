package org.application.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.application.model.NotificationRequestDTO;
import org.application.service.NotificationService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NotificationConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "user_management_topic",groupId = "groupid")
    public void consumerNotification(ConsumerRecord<String,Object>  consumedRecord) throws JsonProcessingException {
        NotificationRequestDTO notificationRequestDTO=   new ObjectMapper().readValue(consumedRecord.value().toString(), NotificationRequestDTO.class);
        notificationService.sendNotification(notificationRequestDTO);
    }

}
