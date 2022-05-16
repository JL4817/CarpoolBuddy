package com.example.carpoolbuddy.Models;

import android.os.Parcel;

import java.util.ArrayList;


public class Plane extends Vehicle{

    private int planeSize;

    public Plane() {

    }

    public Plane(int planeSize) {
        this.planeSize = planeSize;
    }


    public Plane(String location, String model, int capacity, int price, boolean open, String type, String id, int planeSize) {
        super(location, model, capacity, price, open, type, id);
        this.planeSize = planeSize;
    }

    public Plane(Parcel in, int planeSize) {
        super(in);
        this.planeSize = planeSize;
    }


    public static final Creator<Plane> CREATOR = new Creator<Plane>() {
        @Override
        public Plane createFromParcel(Parcel in) {
            return new Plane(in);
        }

        @Override
        public Plane[] newArray(int size) {
            return new Plane[size];
        }
    };


    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(planeSize);
    }


    private Plane(Parcel in) {
        super(in);
        planeSize = in.readInt();
    }


    @Override
    public String toString() {
        return "Plane{" +
                "planeSize=" + planeSize +
                '}';
    }

    public int getPlaneSize() {
        return planeSize;
    }

    public void setPlaneSize(int planeSize) {
        this.planeSize = planeSize;
    }


}
