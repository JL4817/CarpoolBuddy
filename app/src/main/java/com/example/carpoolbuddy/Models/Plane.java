package com.example.carpoolbuddy.Models;

public class Plane extends Vehicle{

    private int planeSize;


    public Plane(String location, String model, int capacity, int price, boolean open, String type, int planeSize) {
        super(location, model, capacity, price, open, type);
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
