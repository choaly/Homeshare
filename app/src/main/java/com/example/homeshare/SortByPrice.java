package com.example.homeshare;

import java.util.Comparator;

// Helper class implementing Comparator interface
public class SortByPrice implements Comparator<Listing> {
    // Sorting in ascending order of price

    public int compare(Listing a, Listing b)
    {
        return Double.compare(a.getPrice(), b.getPrice());
    }
}