package com.example.homeshare;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class SortByPriceTest {
    @Test
    public void sortedLowToHigh() {
        Listing listing1 = new Listing("","","","","","","","","","",10.0,0, 0, 0, 0);
        Listing listing2 = new Listing("","","","","","","","","","",9.0,0, 0, 0,0);

        // Creating a list of these listings (unsorted)
        ArrayList<Listing> list = new ArrayList<>();
        list.add(listing1);
        list.add(listing2);
        Collections.sort(list, new SortByPrice());

        // Manually sort elements
        ArrayList<Listing> sorted = new ArrayList<>(Arrays.asList(listing2, listing1));

        Assert.assertArrayEquals(sorted.toArray(), list.toArray());
    }

    @Test
    public void sortedHighToLow() {
        Listing listing1 = new Listing("","","","","","","","","","",8.0,0, 0,0,0);
        Listing listing2 = new Listing("","","","","","","","","","",19.0,0,0,0,0);

        // Creating a list of these listings (unsorted)
        ArrayList<Listing> list = new ArrayList<>();
        list.add(listing1);
        list.add(listing2);
        Collections.sort(list, Collections.reverseOrder(new SortByPrice()));

        // Manually sort elements
        ArrayList<Listing> sorted = new ArrayList<>(Arrays.asList(listing2, listing1));

        Assert.assertArrayEquals(sorted.toArray(), list.toArray());
    }
}