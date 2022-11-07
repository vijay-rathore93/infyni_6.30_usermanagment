package org.application.util;


import org.application.model.NotificationRequestDTO;
import org.application.model.NotificationType;
import org.application.model.UserBasicInfoDTO;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    public NotificationRequestDTO getNotificationRequest(UserBasicInfoDTO userBasicInfoDTO) {
        return NotificationRequestDTO.builder()
                .name(userBasicInfoDTO.getName())
                .email(userBasicInfoDTO.getEmail())
                .contact(userBasicInfoDTO.getContact())
                .token(userBasicInfoDTO.getToken())
                .notificationType(NotificationType.EMAIL).build();
    }

}
