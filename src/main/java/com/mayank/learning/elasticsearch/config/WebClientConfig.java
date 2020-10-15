package com.mayank.learning.elasticsearch.config;

/*
    File Name : WebClientConfig.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 09:10
*/

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "webClientElasticSearch")
    public WebClient webClientElasticSearch(){
        return WebClient.builder().baseUrl("http://localhost:9200")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();
    }

}
