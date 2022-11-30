package com.example.homeshare;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.String;
import java.util.ArrayList;
import java.util.Comparator;

public class Listing {


    String id, title, description, address, leaseStart, leaseEnd, prefGender, posterName, posterId, responseDeadline;
    int numSpotsAvail, numBeds, numBaths;
    double price, distToCampus;
    DataSnapshot responses, confirmedMatches;


    public Listing() {
    }

    public Listing(String id, String title, String description, String address, String leaseStart, String leaseEnd, String prefGender, String posterName, String posterId, String responseDeadline, double price, int numSpotsAvail, int numBeds, int numBaths, double distToCampus) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.address = address;
        this.leaseStart = leaseStart;
        this.leaseEnd = leaseEnd;
        this.prefGender = prefGender;
        this.posterName = posterName;
        this.posterId = posterId;
        this.responseDeadline = responseDeadline;
        this.numSpotsAvail = numSpotsAvail;
        this.price = price;
        this.numBeds = numBeds;
        this.numBaths = numBaths;
        this.distToCampus = distToCampus;
    }


    public String getPosterId() {
        return posterId;
    }

    public String getResponseDeadline() {
        return responseDeadline;
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

    public int getNumBeds() {
        return numBeds;
    }

    public int getNumBaths() {
        return numBaths;
    }

    public double getDistToCampus() {
        return distToCampus;
    }
}
