package com.example.carpoolbuddy.Models;

public class RV extends Vehicle{

    private int nrOfRooms;


    public RV(String location, String model, int capacity, int price, boolean open, int nrOfRooms) {
        super(location, model, capacity, price, open);
        this.nrOfRooms = nrOfRooms;
    }

    public int getNrOfRooms() {
        return nrOfRooms;
    }

    public void setNrOfRooms(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    @Override
    public String toString() {
        return "RV{" +
                "nrOfRooms=" + nrOfRooms +
                '}';
    }

}
