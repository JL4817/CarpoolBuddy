package com.example.carpoolbuddy.Models;

import android.os.Parcel;

import com.example.carpoolbuddy.R;

public class RV extends Vehicle{

    private int nrOfRooms;

    public RV(){

    }

    public RV(int nrOfRooms) {
        this.nrOfRooms = nrOfRooms;
    }

    public RV(String location, String model, int capacity, int price, boolean open, String type, String id, int nrOfRooms, String owner) {
        super(location, model, capacity, price, open, type, id, owner);
        this.nrOfRooms = nrOfRooms;
    }

    public RV(Parcel in, int nrOfRooms) {
        super(in);
        this.nrOfRooms = nrOfRooms;
    }

    public static final Creator<RV> CREATOR = new Creator<RV>() {
        @Override
        public RV createFromParcel(Parcel in) {
            return new RV(in);
        }

        @Override
        public RV[] newArray(int size) {
            return new RV[size];
        }
    };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeInt(nrOfRooms);
    }

    private RV(Parcel in) {
        super(in);
        nrOfRooms = in.readInt();
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
