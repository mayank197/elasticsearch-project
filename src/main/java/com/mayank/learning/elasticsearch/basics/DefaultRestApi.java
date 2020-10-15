package com.mayank.learning.elasticsearch.basics;

/*
    File Name : DefaultApi.java
    
    @author Mayank Sharma
    First Created on 14-10-2020 at 09:58
*/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DefaultRestApi {

    private static final Logger logger = LoggerFactory.getLogger(DefaultRestApi.class);

    @RequestMapping("/welcome")
    public String welcome(){
        logger.info("Inside welcome method");
        return "Hey!! Welcome to Spring!! ";
    }

    @GetMapping(value = "/header-one")
    public String headerByAnnotation(@RequestHeader(name="User-agent") String headerUserAgent,
                                     @RequestHeader(name="elastic-search-header", required = false) String header){
        return "User-agent : " +headerUserAgent + ", Elastic Header : " +header;
    }

    @GetMapping(value = "/header-two")
    public String headerByRequest(ServerHttpRequest request){
        return "User-agent : " + request.getHeaders().getValuesAsList("User-agent") +
                ", elastic-search-header : "+request.getHeaders()
                                                .getValuesAsList("elastic-search-header");
    }

}
