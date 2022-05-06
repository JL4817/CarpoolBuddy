package com.example.carpoolbuddy.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Vehicle implements Parcelable{
    private String location;
    private String model;
    private int capacity;
    private int price;
    private boolean open;
    private String type;

    //i dont have open, is that fine?

    public Vehicle(){
        this.location = "";
        this.model = "";
        this.capacity = 0;
        this.price = 0;
        this.open = false;
        this.type = "";
    }

    public Vehicle(String location, String model, int capacity, int price, boolean open, String type){
        this.location = location;
        this.model = model;
        this.capacity = capacity;
        this.price = price;
        this.open = open;
        this.type = type;
    }


    protected Vehicle(Parcel in) {
        location = in.readString();
        model = in.readString();
        capacity = in.readInt();
        price = in.readInt();
        open = in.readByte() != 0;
        type = in.readString();
    }

    public static final Creator<Vehicle> CREATOR = new Creator<Vehicle>() {
        @Override
        public Vehicle createFromParcel(Parcel in) {
            return new Vehicle(in);
        }

        @Override
        public Vehicle[] newArray(int size) {
            return new Vehicle[size];
        }
    };

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Override
    public String toString() {
        return "Vehicle{" +
                "location='" + location + '\'' +
                ", model='" + model + '\'' +
                ", capacity=" + capacity +
                ", price=" + price +
                ", open=" + open +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(model);
        dest.writeInt(capacity);
        dest.writeInt(price);
        dest.writeByte((byte) (open ? 1 : 0));
        dest.writeString(type);
    }
}
