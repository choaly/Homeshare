package com.example.homeshare;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

public class CreateListingWhiteBox {
    private final String first_name = "Alyssa";

    @Before
    public void setup() {
        //login userA with correct credentials
        //Presses “+” button on bottom bar
    }

    @Test
    public void createListingWrongDateFormat() {
        //- make sure error message pops up for wrong date format
        //make context so Firebase can be instantiated
        String wrong_date_format = "1/2/00";
    }
}
