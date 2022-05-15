package com.example.carpoolbuddy.Models;

import android.os.Parcel;

import java.util.ArrayList;

public class SportsCar extends Vehicle{

    private int maxSpeed;

    public SportsCar(){

    }

    public SportsCar(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public SportsCar(String location, String model, int capacity, int price, boolean open, String type, int maxSpeed) {
        super(location, model, capacity, price, open, type);
        this.maxSpeed = maxSpeed;
    }

    public SportsCar(Parcel in, int maxSpeed) {
        super(in);
        this.maxSpeed = maxSpeed;
    }

    public static final Creator<SportsCar> CREATOR = new Creator<SportsCar>() {
        @Override
        public SportsCar createFromParcel(Parcel in) {
            return new SportsCar(in);
        }

        @Override
        public SportsCar[] newArray(int size) {
            return new SportsCar[][size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(maxSpeed);
    }

    private SportsCar(Parcel in) {
        super(in);
        maxSpeed = in.readInt();
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
