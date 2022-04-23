package com.example.carpoolbuddy.Models;

public class Alumni {

    private String nameString;
    private String emailString;
    private int gradYearInt;

    public Alumni(String uid, String nameString, String emailString, int gradYearInt){

    }


    public Alumni(String nameString, String emailString, int gradYearInt){
        this.nameString = nameString;
        this.emailString = emailString;
        this.gradYearInt = gradYearInt;
    }


    public String getNameString() {
        return nameString;
    }

    public void setNameString(String nameString) {
        this.nameString = nameString;
    }

    public String getEmailString() {
        return emailString;
    }

    public void setEmailString(String emailString) {
        this.emailString = emailString;
    }

    public int getGradYearInt() {
        return gradYearInt;
    }

    public void setGradYearInt(int gradYearInt) {
        this.gradYearInt = gradYearInt;
    }

    @Override
    public String toString() {
        return "Alumni{" +
                "nameString='" + nameString + '\'' +
                ", emailString='" + emailString + '\'' +
                ", gradYearInt='" + gradYearInt + '\'' +
                '}';
    }
}
