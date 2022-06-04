package com.example.carpoolbuddy.Models;

import java.util.ArrayList;

public class User {

    private String email;
    private String name;
    private ArrayList<String> ownedVehicles;
    private String uid;


    public User(){

    }

    public User(String uid, String email, String name){
        this.email = email;
        this.name = name;
        ownedVehicles = new ArrayList<>();
        this.uid = uid;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public ArrayList<String> getOwnedVehicles() {
        return ownedVehicles;
    }

    public void setOwnedVehicles(ArrayList<String> ownedVehicles) {
        this.ownedVehicles = ownedVehicles;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }


    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", ownedVebicles=" + ownedVehicles +
                ", uid='" + uid + '\'' +
                '}';
    }
}
