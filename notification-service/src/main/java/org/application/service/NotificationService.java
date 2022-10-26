package org.application.service;

import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;

public interface NotificationService {

    SuccessResponse sendNotification(NotificationRequestDTO notificationRequestDTO);
}
