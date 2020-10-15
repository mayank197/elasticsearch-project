package com.mayank.learning.elasticsearch.service;

/*
    File Name : CarService.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 00:02
*/

import com.mayank.learning.elasticsearch.entity.Car;

import java.util.Arrays;
import java.util.List;

public interface CarService {

    List<String> brands = Arrays.asList("Toyota","Honda","Ford","BMW","Mitsubishi","Hyundai");
    List<String> colors =  Arrays.asList("Red", "Black", "White","Blue","Silver");
    List<String> types = Arrays.asList("Sedan","SUV","MPV","Hatchback","Convertible");
    List<String> additionalFeaturesList = Arrays.asList("GPS","Alarm","Sunroof","Media Player","Leather Seats");
    List<String> fuels = Arrays.asList("Petrol","Electric","Hybrid");
    List<String> tyreManufacturers = Arrays.asList("GoodYear","Bridgestone","Ceat");

    Car generateCar();

}
