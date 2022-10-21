package org.application.service;

import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.application.util.NotificationBuilder;

public interface NotificationService {

    SuccessResponse sendNotification(NotificationBuilder notificationBuilder);
}
