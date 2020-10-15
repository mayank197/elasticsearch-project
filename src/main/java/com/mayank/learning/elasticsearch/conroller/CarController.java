package com.mayank.learning.elasticsearch.conroller;

/*
    File Name : CarController.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 00:10
*/

import com.mayank.learning.elasticsearch.entity.Car;
import com.mayank.learning.elasticsearch.repository.CarRepository;
import com.mayank.learning.elasticsearch.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping(value = "/api/car/v1")
public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private CarService carService;

    @GetMapping(value = "/random", produces = MediaType.APPLICATION_JSON_VALUE)
    public Car random(){
        return carService.generateCar();
    }

    @PostMapping(value = "/echo", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String echoCar(@RequestBody  Car car){
        logger.info("Car : {}", car);
        return car.toString();
    }

    @GetMapping(value = "/random-cars", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> randomCars(){
        List<Car> cars = new ArrayList<>();
        for(int i=0;i< ThreadLocalRandom.current().nextInt(1,10);i++){
            cars.add(carService.generateCar());
        }
        return cars;
    }

    @GetMapping(value = "/count")
    public String countCars(){
        return "There are " +carRepository.count() + " cars";
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String saveCar(@RequestBody Car car){
         String id = carRepository.save(car).getId();
         return "Saved with Id "+id;
    }

    @GetMapping(value = "/{id}")
    public Car getCar(@PathVariable("id") String carId){
        return carRepository.findById(carId).orElse(null);
    }

    @PutMapping(value = "/{id}")
    public String updateCar(@PathVariable("id") String carId, @RequestBody Car updatedCar){
        updatedCar.setId(carId);
        return "Updated Car with Id " +carRepository.save(updatedCar).getId();
    }

}
