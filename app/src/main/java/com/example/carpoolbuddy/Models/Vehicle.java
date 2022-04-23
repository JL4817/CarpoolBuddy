package com.example.carpoolbuddy.Models;

public class Vehicle {
    private String location;
    private String model;
    private int capacity;
    private String vehicleType;
    private int price;


    public Vehicle(){

    }


    public Vehicle(String location, String model, int capacity, String vehicleType, int price){
        this.location = location;
        this.model = model;
        this.capacity = capacity;
        this.vehicleType = vehicleType;
        this.price = price;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "location='" + location + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", vehicleType='" + vehicleType + '\'' +
                ", price=" + price +
                '}';
    }
}
