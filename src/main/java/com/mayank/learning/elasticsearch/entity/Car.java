package com.mayank.learning.elasticsearch.entity;

/*
    File Name : Car.java
    
    @author Mayank Sharma
    First Created on 14-10-2020 at 23:59
*/

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.time.LocalDate;
import java.util.List;

// We're using this class for Elastic Search
// To work with data storage, this class must be annotated with @Document
// Each elastic search entity must have unique identifier

@Document(indexName = "elastic-project")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Car {

    @Id
    private String id;

    private String brand;
    private String color;
    private String type;
    private double price;
    private boolean available;

    @JsonInclude(value = Include.NON_EMPTY)
    private String secretFeature;

    /*
        Date Type in Java is represented in Elastic Search using milliseconds
        So, we have to modify our JsonFormat annotation

        Current date type has Space, Elastic Search may treat it as Text
        We need to mark the date field with @Field and pass proper ES data format
     */

    @Field(type = FieldType.Date, format = DateFormat.date)
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Kolkata")
    private LocalDate releaseDate;

    @JsonUnwrapped                      // Now, it'll be unwrapped in the Json and will look like its part of Car only
    private Engine engine;
    private List<Tyre> tyres;

    @JsonInclude(value = Include.NON_EMPTY)
    private List<String> additionalFeatures;

    public Car() {
    }

    public Car(String brand, String color, String type) {
        this.brand = brand;
        this.color = color;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Tyre> getTyres() {
        return tyres;
    }

    public void setTyres(List<Tyre> tyres) {
        this.tyres = tyres;
    }

    public String getSecretFeature() {
        return secretFeature;
    }

    public void setSecretFeature(String secretFeature) {
        this.secretFeature = secretFeature;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getAdditionalFeatures() {
        return additionalFeatures;
    }

    public void setAdditionalFeatures(List<String> additionalFeatures) {
        this.additionalFeatures = additionalFeatures;
    }

    @Override
    public String toString() {
        return "Car[" + "brand='" + brand + '\'' + ", color='" + color + '\'' + ", type='" + type + '\'' + ", price=" + price + ", available=" + available + ", releaseDate=" + releaseDate + ']';
    }
}
