package com.mayank.learning.elasticsearch.service.implementation;

/*
    File Name : CarServiceImpl.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 00:04
*/

import com.mayank.learning.elasticsearch.entity.Car;
import com.mayank.learning.elasticsearch.entity.Engine;
import com.mayank.learning.elasticsearch.entity.Tyre;
import com.mayank.learning.elasticsearch.service.CarService;
import com.mayank.learning.elasticsearch.utils.RandomDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CarServiceImpl implements CarService {

    private static final Logger logger = LoggerFactory.getLogger(CarServiceImpl.class);

    @Override
    public Car generateCar() {
        String brand = brands.get(ThreadLocalRandom.current().nextInt(0, brands.size()));
        String color = colors.get(ThreadLocalRandom.current().nextInt(0, colors.size()));
        String type = types.get(ThreadLocalRandom.current().nextInt(0, types.size()));

        boolean available = ThreadLocalRandom.current().nextBoolean();
        double price = ThreadLocalRandom.current().nextDouble(5000, 12000);
        LocalDate firstReleasedDate = RandomDateUtil.generateRandomLocalDate();

        int randomCount = ThreadLocalRandom.current().nextInt(additionalFeaturesList.size());
        List<String> additionalFeatures = new ArrayList<>();

        for(int i=0;i<randomCount;i++){
            additionalFeatures.add(additionalFeaturesList.get(i));
        }

        String fuel = fuels.get(ThreadLocalRandom.current().nextInt(fuels.size()));
        int horsePower = ThreadLocalRandom.current().nextInt(100,221);

        Engine engine = new Engine();
        engine.setFuelType(fuel);
        engine.setHorsePower(horsePower);

        List<Tyre> tyres = new ArrayList<>();
        for(int i=0;i<3;i++){
            Tyre tyre = new Tyre();
            String manufacturer = tyreManufacturers.get(ThreadLocalRandom.current().nextInt(tyreManufacturers.size()));
            int size = ThreadLocalRandom.current().nextInt(15,18);
            int tyrePrice = ThreadLocalRandom.current().nextInt(200,401);
            tyre.setManufacturer(manufacturer);
            tyre.setPrice(tyrePrice);
            tyre.setSize(size);
            tyres.add(tyre);
        }

        String secretFeature = ThreadLocalRandom.current().nextBoolean() ? "can fly" : null;

        Car car = new Car(brand, color, type);
        car.setAvailable(available);
        car.setPrice(price);
        car.setReleaseDate(firstReleasedDate);
        car.setAdditionalFeatures(additionalFeatures);
        car.setEngine(engine);
        car.setTyres(tyres);
        return car;
    }

}
