package org.application.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.application.service.EmailNotificationService;
import org.application.service.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/notification")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<SuccessResponse> sendNotification(@RequestBody NotificationRequestDTO request){
       SuccessResponse  response= notificationService.sendNotification(request);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
