package com.example.homeshare;

import java.lang.String;
import java.util.ArrayList;

public class Listing {
    String id, title, description, address, leaseStart, leaseEnd, prefGender, posterName;
    int numSpotsAvail;
    double price;

    public Listing() {
    }

    public Listing(String id, String title, String description, String address, String leaseStart, String leaseEnd, String prefGender, String posterName, double price, int numSpotsAvail) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.prefGender = prefGender;
        this.posterName = posterName;
        this.price = price;
        this.numSpotsAvail = numSpotsAvail;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAddress() {
        return address;
    }

    public String getLeaseStart() {
        return leaseStart;
    }

    public String getLeaseEnd() {
        return leaseEnd;
    }

    public String getDescription() {
        return description;
    }

    public String getPrefGender() {
        return prefGender;
    }

    public String getPosterName() {
        return posterName;
    }

    public double getPrice() {
        return price;
    }

    public int getNumSpotsAvail() {
        return numSpotsAvail;
    }

}
