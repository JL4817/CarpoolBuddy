package com.example.carpoolbuddy.Models;

public class ElectricCar extends Vehicle{

    public ElectricCar(String location, String model, int capacity, int price) {
        super(location, model, capacity, price);
    }

    @Override
    public String toString() {
        return "ElectricCar{}";
    }

}
