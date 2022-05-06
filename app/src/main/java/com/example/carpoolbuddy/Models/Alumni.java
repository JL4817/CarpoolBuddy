package com.example.carpoolbuddy.Models;

public class Alumni extends User{

    private int gradYearInt;

    public Alumni(String email, String name, int gradYearInt) {
        super(email, name);
        this.gradYearInt = gradYearInt;
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
                "gradYearInt=" + gradYearInt +
                '}';
    }
}
