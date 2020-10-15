package com.mayank.learning.elasticsearch.repository;

/*
    Class for accessing and modifying data in Elastic Search
    With Spring Data, we just need to create an interface and Spring Data will automatically
    handle the rest of communication, including creating proper query or data manipulation
 */

import com.mayank.learning.elasticsearch.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CarRepository extends ElasticsearchRepository<Car, String> {

    Page<Car> findByBrandAndColor(String brand, String color, Pageable pageable);
    Page<Car> findByReleaseDateAfter(LocalDate date, Pageable pageable);

}
