package com.example.carpoolbuddy.Models;

public class Teacher extends User{
    private int teaAge;


    public Teacher(String userId, String email, String name, int teaAge) {
        super(userId, email, name);
        this.teaAge = teaAge;
    }

    public int getTeaAge() {
        return teaAge;
    }

    public void setTeaAge(int teaAge) {
        this.teaAge = teaAge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teaAge=" + teaAge +
                '}';
    }

}
