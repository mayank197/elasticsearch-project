package com.mayank.learning.elasticsearch.common;

/*
    File Name : CarElasticDataSource.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 09:25
*/

import com.mayank.learning.elasticsearch.entity.Car;
import com.mayank.learning.elasticsearch.repository.*;
import com.mayank.learning.elasticsearch.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Component
public class CarElasticDataSource {

    private static final Logger logger = LoggerFactory.getLogger(CarElasticDataSource.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    @Qualifier("carServiceImpl")
    private CarService carService;

    @Autowired
    @Qualifier("webClientElasticSearch")
    private WebClient webClient;

    // Will invoke this method on application startup
    @EventListener(ApplicationReadyEvent.class)
    public void populateData(){
        // To delete existing ES data, we'll consume ES API
        String response = webClient.delete().uri("/elastic-project").retrieve()
                .bodyToMono(String.class).block();

        logger.info("Delete Functionality ended with response {}", response);

        List<Car> cars = new ArrayList<>();
        IntStream.rangeClosed(1, 10000).forEach(i-> cars.add(carService.generateCar()));

        carRepository.saveAll(cars);
        logger.info("Saved {} cars on Elastic Search", carRepository.count());

    }


}
