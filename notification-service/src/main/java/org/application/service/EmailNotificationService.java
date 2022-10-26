package org.application.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import static org.application.util.Constants.ACTIVATION_MAIL_SUBJECT;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailNotificationService implements NotificationService {

    private final JavaMailSender javaMailSender;

    @Override
    public SuccessResponse sendNotification(NotificationRequestDTO notificationRequestDTO) {
        String url = "http://localhost:10002/user-management/api/v1/user/auth/" + notificationRequestDTO.getToken();
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notificationRequestDTO.getEmail());
        simpleMailMessage.setSubject(ACTIVATION_MAIL_SUBJECT);
        simpleMailMessage.setText(url);
       // javaMailSender.send(simpleMailMessage);

        log.info("activation mail::"+url);
        return SuccessResponse.builder().message("Email Sent..").build();
    }
}
