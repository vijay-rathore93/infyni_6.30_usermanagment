package org.application.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.application.model.SuccessResponse;
import org.application.model.UserBasicInfoDTO;
import org.application.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserBasicInfoDTO> processUserData(@RequestBody UserBasicInfoDTO userBasicInfoDTO) throws JsonProcessingException {
        log.info("Saving the User info started....");
        UserBasicInfoDTO createdData = userService.saveUserInfo(userBasicInfoDTO);
        return new ResponseEntity<>(createdData, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserBasicInfoDTO>> getAllData() {
        log.info("getAllData the User info started....");
        List<UserBasicInfoDTO> response = userService.getAllData();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("{userId}")
    public ResponseEntity<UserBasicInfoDTO> getSingleUserData(@PathVariable String userId) {
        log.info("getSingleUserData  started....");
        UserBasicInfoDTO response = userService.getSingleUserData(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("{userId}")
    public ResponseEntity<SuccessResponse> deleteSingleUserInfo(@PathVariable String userId) {
        log.info("getSingleUserData  started....");
        SuccessResponse response = userService.deleteSingleUserInfo(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @GetMapping("/auth/{token}")
    public ResponseEntity<SuccessResponse> activateUser(@PathVariable String token) {
        log.info("User activation started  started....");
        SuccessResponse response = userService.activateUser(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}




