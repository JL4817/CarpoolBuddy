package com.example.carpoolbuddy.Models;

public class Student {

    private String stuEmail;
    private String stuName;
    private String stuPass;

    private int grade;

    public Student(){
    }

    public Student(String stuEmail, String stuName, String stuPass, int grade){
        this.stuEmail = stuEmail;
        this.stuName = stuName;
        this.stuPass = stuPass;
        this.grade = grade;
    }


    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuPass() {
        return stuName;
    }

    public void setStuPass(String stuPass) {
        this.stuName = stuName;
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

    @Override
    public String toString() {
        return "Student{" +
                "stuEmail='" + stuEmail + '\'' +
                ", stuName='" + stuName + '\'' +
                ", stuPass='" + stuPass + '\'' +
                ", grade=" + grade +
                '}';
    }
}
