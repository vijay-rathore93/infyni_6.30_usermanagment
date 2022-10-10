package org.application.exception;

import org.application.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        log.error("Exception caught in global exception handler error message::{}",exception.getMessage());
        ErrorResponse response = ErrorResponse
                .builder()
                .message(exception.getMessage())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException userException) {
        log.error("Exception caught in global exception handler error message::{}",userException.getErrorMessage());
        ErrorResponse response = ErrorResponse
                .builder()
                .message(userException.getErrorMessage())
                .status(userException.getStatus())
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
