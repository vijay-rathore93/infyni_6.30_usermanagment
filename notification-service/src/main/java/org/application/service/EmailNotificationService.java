package org.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.application.util.NotificationBuilder;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService implements  NotificationService{

    private final JavaMailSender javaMailSender;

    @Override
    public SuccessResponse sendNotification(NotificationBuilder notificationBuilder) {
        return null;
    }
}
