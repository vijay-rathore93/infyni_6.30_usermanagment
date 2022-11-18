package org.application.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.entity.UserBasicInfo;
import org.application.exception.UserException;
import org.application.model.NotificationRequestDTO;
import org.application.model.SuccessResponse;
import org.application.model.UserBasicInfoDTO;
import org.application.repo.UserRepo;
import org.application.util.UserUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.application.util.Constants.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    private final ModelMapper modelMapper;

    private final RestTemplate restTemplate;
    @Autowired
    private UserUtil userUtil;

  private final NotificationServiceClient notificationServiceClient;

    private final KafkaTemplate<String, Object> kafkaTemplate;


    @Value("${notification.endpoint}")
    private String notificationEndPoint;

    public UserBasicInfoDTO saveUserInfo(UserBasicInfoDTO userBasicInfoDTO) throws JsonProcessingException {
        /* userBasicInfoDTO.setUserId(UUID.fromString(userBasicInfoDTO.getContact()+ Instant.now().toString()).toString());*/
        userBasicInfoDTO.setUserId(UUID.randomUUID().toString());
        userBasicInfoDTO.setToken(UUID.randomUUID() + userBasicInfoDTO.getUserId());


        UserBasicInfo entityTobeCreated = modelMapper.map(userBasicInfoDTO, UserBasicInfo.class);
        UserBasicInfo entityCreated = userRepo.save(entityTobeCreated);
        UserBasicInfoDTO response = modelMapper.map(entityCreated, UserBasicInfoDTO.class);


        //notification request creation
        NotificationRequestDTO notificationRequestDTO = userUtil.getNotificationRequest(response);

      // kafkaTemplate.send("user_management_topic",new ObjectMapper().writeValueAsString(notificationRequestDTO));



   /*     HttpHeaders httpHeaders=new HttpHeaders();
       // httpHeaders.set("authorization","JWT_TOKEN");

        HttpEntity<NotificationRequestDTO> httpEntity=new HttpEntity<>(notificationRequestDTO,httpHeaders);



        //making the sync call to Notification service
        ResponseEntity<SuccessResponse> responseResponseEntity = restTemplate
                .postForEntity(notificationEndPoint,
                        httpEntity, SuccessResponse.class);*/


      ResponseEntity<SuccessResponse> responseResponseEntity=  notificationServiceClient.sendNotification(notificationRequestDTO);
        if (responseResponseEntity.getStatusCodeValue() != HttpStatus.ACCEPTED.value()) {
            throw new UserException("Notification sending failed....",NOTIFICATION_FAILED);
        }


        return response;
    }

    public List<UserBasicInfoDTO> getAllData() {
        List<UserBasicInfo> allData = userRepo.findAll();
        return allData.stream().map(obj ->
                modelMapper.map(obj, UserBasicInfoDTO.class)
        ).collect(Collectors.toList());
    }

    public UserBasicInfoDTO getSingleUserData(String userId) {
        UserBasicInfo userBasicInfo = userRepo.findByUserId(userId).orElseThrow(() -> new UserException(MESSAGE, USER_NOT_FOUND_CODE));
        return modelMapper.map(userBasicInfo, UserBasicInfoDTO.class);

    }

    public SuccessResponse deleteSingleUserInfo(String userId) {
        UserBasicInfo userBasicInfo = userRepo.findByUserId(userId).orElseThrow(() -> new UserException(MESSAGE, USER_NOT_FOUND_CODE));
        userRepo.delete(userBasicInfo);
        SuccessResponse successResponse = SuccessResponse.builder().message(USER_DELETE_MESSAGE).build();
        return successResponse;
    }

    public SuccessResponse activateUser(String token) {
        UserBasicInfo userBasicInfo = userRepo.findByToken(token)
                .orElseThrow(() ->
                        new UserException(TOKEN_NOT_FOUND, TOKEN_NOT_FOUND_CODE));
        userBasicInfo.setActive(true);
        userRepo.save(userBasicInfo);
        return SuccessResponse.builder().message(ACTIVATED_MESSAGE).build();


    }
}
