package com.example.carpoolbuddy.Models;

public class SportsCar extends Vehicle{

    private int maxSpeed;


    public SportsCar(String location, String model, int capacity, int price, boolean open, String type, int maxSpeed) {
        super(location, model, capacity, price, open, type);
        this.maxSpeed = maxSpeed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "SportsCar{" +
                "maxSpeed=" + maxSpeed +
                '}';
    }

}
