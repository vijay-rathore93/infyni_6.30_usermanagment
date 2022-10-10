package org.application.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.application.exception.UserException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.application.util.Constants.GENERAL_ERROR;


@Slf4j
@RequestMapping(value = "/user")
@RestController
@RequiredArgsConstructor
public class UserController {



    @GetMapping
    public String getMessage(){
        throw new UserException("Testing error",GENERAL_ERROR);
    }


}




