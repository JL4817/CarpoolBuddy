package com.example.carpoolbuddy.Models;

public class Plane extends Vehicle{

    private int planeSize;


    public Plane(String location, String model, int capacity, int price, int planeSize) {
        super(location, model, capacity, price);
        this.planeSize = planeSize;
    }

    public int getPlaneSize() {
        return planeSize;
    }

    public void setPlaneSize(int planeSize) {
        this.planeSize = planeSize;
    }

    @Override
    public String toString() {
        return "Plane{" +
                "planeSize=" + planeSize +
                '}';
    }
}
