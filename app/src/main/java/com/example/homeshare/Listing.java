package com.example.homeshare;

import java.lang.String;
import java.util.ArrayList;

public class Listing {
    String listId, title, description, address, leaseStart, leaseEnd, prefGender, posterName;
    int price, numBed, numBath, numSpotsAvail;

    public String getListId() {
        return listId;
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

    public int getPrice() {
        return price;
    }

    public int getNumBed() {
        return numBed;
    }

    public int getNumBath() {
        return numBath;
    }

    public int getNumSpotsAvail() {
        return numSpotsAvail;
    }

}
