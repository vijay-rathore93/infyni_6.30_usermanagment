package org.application.service;

import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notification", path = "http://localhost:10003/notification/api/v1/notification")
public interface NotificationServiceClient {
    @PostMapping("/send")
    ResponseEntity<SuccessResponse> sendNotification(@RequestBody NotificationRequestDTO request);
}

