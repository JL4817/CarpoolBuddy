package com.example.carpoolbuddy.Models;

public class Parent extends User{

    private int nrOfKids;


    public Parent(String email, String name, int nrOfKids) {
        super(email, name);
        this.nrOfKids = nrOfKids;
    }

    public int getNrOfKids() {
        return nrOfKids;
    }

    public void setNrOfKids(int nrOfKids) {
        this.nrOfKids = nrOfKids;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "nrOfKids=" + nrOfKids +
                '}';
    }



}
