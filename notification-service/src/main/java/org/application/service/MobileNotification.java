package org.application.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.application.util.NotificationBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MobileNotification implements  NotificationService{

    @Override
    public SuccessResponse sendNotification(NotificationRequestDTO notificationRequestDTO) {
        return null;
    }
}
