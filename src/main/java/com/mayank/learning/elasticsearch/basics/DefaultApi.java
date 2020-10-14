package com.mayank.learning.elasticsearch.basics;

/*
    File Name : DefaultApi.java
    
    @author Mayank Sharma
    First Created on 14-10-2020 at 09:58
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DefaultApi {

    private static final Logger logger = LoggerFactory.getLogger(DefaultApi.class);

    @RequestMapping("/welcome")
    public String welcome(){
        return "Hey!! Welcome to Spring!! ";
    }

}
