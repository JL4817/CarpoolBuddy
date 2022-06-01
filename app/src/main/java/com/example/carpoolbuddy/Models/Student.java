package com.example.carpoolbuddy.Models;

import android.widget.EditText;

public class Student extends User{

    private int grade;


    public Student(String userId, String email, String name, int grade) {
        super(userId, email, name);
        this.grade = grade;
    }


    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade=" + grade +
                '}';
    }



}
