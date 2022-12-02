package com.example.homeshare;

import java.util.Comparator;

public class SortByDistance implements Comparator<Listing> {
    // Sorting in ascending order of distance (shortest to longest distance)
    public int compare(Listing a, Listing b)
    {
        return Double.compare(a.getDistToCampus(), b.getDistToCampus());
    }
}