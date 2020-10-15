package com.mayank.learning.elasticsearch.conroller;

/*
    File Name : CarController.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 00:10
*/

import com.mayank.learning.elasticsearch.common.ErrorResponse;
import com.mayank.learning.elasticsearch.common.IllegalApiParamException;
import com.mayank.learning.elasticsearch.entity.Car;
import com.mayank.learning.elasticsearch.repository.CarRepository;
import com.mayank.learning.elasticsearch.service.CarService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @GetMapping(value = "/find-json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<Car> findCarsByBrandAndColor(@RequestBody Car car, @RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "10") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        return carRepository.findByBrandAndColor(car.getBrand(), car.getColor(), pageRequest).getContent();
    }

    @GetMapping(value = "/cars/{brand}/{color}")
    public ResponseEntity<Object> findCarsByPath(@PathVariable String brand, @PathVariable String color,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size){
        if(StringUtils.isNumeric(color)){
            ErrorResponse response = new ErrorResponse("Invalid Color : "+color, LocalDateTime.now());
            return new ResponseEntity<>(response, null, HttpStatus.BAD_REQUEST);
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "price"));
        List<Car> cars = carRepository.findByBrandAndColor(brand, color, pageRequest).getContent();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping(value = "/cars")
    public ResponseEntity<Object> findCarsByParam(@RequestParam String brand, @RequestParam String color,
                                                 @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "10") int size){
        if(StringUtils.isNumeric(color)){
            logger.error("Error occurred!! Sending to exception handler");
            throw new IllegalArgumentException("Invalid Color : "+color);
        }
        if(StringUtils.isNumeric(brand)){
            logger.error("Error occurred!! Sending to exception handler");
            throw new IllegalArgumentException("Invalid Brand : "+brand);
        }
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "price"));
        List<Car> cars = carRepository.findByBrandAndColor(brand, color, pageRequest).getContent();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping(value = "cars/date")
    public List<Car> getCarsReleasedAfter(@RequestParam("releaseDate") @DateTimeFormat(pattern = "yyyy-MM-dd")
                                                      LocalDate releaseDate,
                                          @RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size){
        return carRepository.findByReleaseDateAfter(releaseDate, PageRequest.of(page, size)).getContent();
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    private ResponseEntity<ErrorResponse> handleInvalidColorException(IllegalArgumentException ex) {
        String message = "Exception " + ex.getMessage();
        logger.info(message);
        ErrorResponse errorResponse = new ErrorResponse(message, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(value = IllegalApiParamException.class)
    private ResponseEntity<ErrorResponse> handleIllegalApiParamException(IllegalApiParamException ex) {
        String message = "Exception API Param " + ex.getMessage();
        logger.info(message);
        ErrorResponse errorResponse = new ErrorResponse(message, LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
