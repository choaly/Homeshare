package com.example.homeshare;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CheckCreateListing {

    @Test
    public void nonEmptyInputs_CreateListng_ReturnsTrue() {
        assertTrue(CreateListingFragment.inputsNotEmpty("Listing Title", "123 West 36th Place", "12/12/2022", "12/12/2023", "valid description", "1200", "2", "female", "11/12/2022"));
    }

    @Test
    public void emptyInputs_CreateListng_ReturnsFalse() {
        assertFalse(CreateListingFragment.inputsNotEmpty("", "", "", "", "","", "", "", ""));
    }

    @Test
    public void settingErrorMessage_CreateListng_DateError() {
        assertEquals(CreateListingFragment.setErrorMsg(true), "Date error" );
    }

    @Test
    public void settingErrorMessage_CreateListng_EmptyFields() {
        assertEquals(CreateListingFragment.setErrorMsg(false), "Please fill in all fields" );
    }

}
