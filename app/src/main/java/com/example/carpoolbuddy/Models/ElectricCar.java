package com.example.carpoolbuddy.Models;

public class ElectricCar extends Vehicle{

    private int batterySize;


    public ElectricCar(String location, String model, int capacity, int price, boolean open, int batterySize) {
        super(location, model, capacity, price, open);
        this.batterySize = batterySize;
    }

    public int getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(int batterySize) {
        this.batterySize = batterySize;
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batterySize=" + batterySize +
                '}';
    }
}
