package com.mayank.learning.elasticsearch.common;

/*
    File Name : MyGlobalExceptionHandler.java
    
    @author Mayank Sharma
    First Created on 16-10-2020 at 01:22
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class MyGlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyGlobalExceptionHandler.class);

    @ExceptionHandler(value = IllegalApiParamException.class)
    private ResponseEntity<ErrorResponse> handleIllegalApiParamException(IllegalApiParamException e) {
        String message = "Exception API Param from MyGlobalExceptionHandler, " + e.getMessage();
        logger.warn(message);

        ErrorResponse errorResponse = new ErrorResponse(message, LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
