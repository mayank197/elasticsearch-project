package com.mayank.learning.elasticsearch.entity;

/*
    File Name : Tyre.java
    
    @author Mayank Sharma
    First Created on 15-10-2020 at 01:05
*/

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Tyre {

    private String manufacturer;
    private int size;

    @JsonIgnore
    private int price;

    public Tyre() {
    }

    public Tyre(String manufacturer, int size, int price) {
        this.manufacturer = manufacturer;
        this.size = size;
        this.price = price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
