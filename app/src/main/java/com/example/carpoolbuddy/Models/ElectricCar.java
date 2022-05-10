package com.example.carpoolbuddy.Models;

import android.os.Parcel;

import java.util.ArrayList;

public class ElectricCar extends Vehicle{

    private int batterySize;


    public ElectricCar(int batterySize) {
        this.batterySize = batterySize;
    }

    public ElectricCar(String location, String model, int capacity, int price, boolean open, String type, int batterySize) {
        super(location, model, capacity, price, open, type);
        this.batterySize = batterySize;
    }

    public ElectricCar(Parcel in, int batterySize) {
        super(in);
        this.batterySize = batterySize;
    }

    @Override
    public String toString() {
        return "ElectricCar{" +
                "batterySize=" + batterySize +
                '}';
    }

    public int getBatterySize() {
        return batterySize;
    }

    public void setBatterySize(int batterySize) {
        this.batterySize = batterySize;
    }
}
