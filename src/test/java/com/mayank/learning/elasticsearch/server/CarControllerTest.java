package com.mayank.learning.elasticsearch.server;

/*
    File Name : CarControllerTest.java
    
    @author Mayank Sharma
    First Created on 16-10-2020 at 23:58
*/

import com.mayank.learning.elasticsearch.entity.Car;
import com.mayank.learning.elasticsearch.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CarControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    @Qualifier("carServiceImpl")
    private CarService carService;

    @Test
    void testRandom() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Testing loop " + i);
            webTestClient.get().uri("/api/car/v1/random").exchange().expectBody(Car.class).value(car -> {
                assertTrue(CarService.brands.contains(car.getBrand()));
                assertTrue(CarService.colors.contains(car.getColor()));
            });
        }
    }

    @Test
    void testSaveCar() {
        Car randomCar = carService.generateCar();

        for (int i = 0; i < 100; i++) {
            assertTimeout(Duration.ofSeconds(1),
                    () -> webTestClient.post().uri("/api/car/v1").contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(randomCar).exchange().expectStatus().is2xxSuccessful());
        }
    }

    @Test
    void testFindCarsByParam() {
        final int size = 5;

        for(String brand : CarService.brands) {
            for(String color : CarService.colors) {
                webTestClient.get().uri(uriBuilder -> uriBuilder.path("/api/car/v1/cars")
                        .queryParam("brand", brand).queryParam("color", color)
                        .queryParam("page", 0)
                        .queryParam("size", size).build()).exchange()
                        .expectBodyList(Car.class).value(cars -> {
                    assertNotNull(cars);
                    assertTrue(cars.size() <= size);
                });

            }
        }
    }

}
