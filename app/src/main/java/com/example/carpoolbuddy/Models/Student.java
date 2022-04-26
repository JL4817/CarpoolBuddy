package com.example.carpoolbuddy.Models;

public class Student {

    private String stuEmail;
    private String stuName;
    private int grade;
    private int id;

    public Student(){
    }

    public Student(int id, String stuEmail, String stuName, int grade){
        this.id = id;
        this.stuEmail = stuEmail;
        this.stuName = stuName;
        this.grade = grade;
    }




    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuEmail='" + stuEmail + '\'' +
                ", stuName='" + stuName + '\'' +
                ", grade=" + grade +
                ", id='" + id + '\'' +
                '}';
    }
}
