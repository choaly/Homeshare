package com.example.homeshare;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

public class loginTest extends TestCase {
    @Test
    public void allFieldsFilledIn() {
        Assert.assertEquals(true, login.allFieldsFilledIn("email@usc.edu", "password"));
        Assert.assertEquals(false, login.allFieldsFilledIn("", "password"));
        Assert.assertEquals(false, login.allFieldsFilledIn("email@usc.edu", ""));
        Assert.assertEquals(false, login.allFieldsFilledIn("", ""));
    }

    @Test
    public void checkUscAcct() {
        Assert.assertEquals(true, login.isUscEmail("user@usc.edu"));
        Assert.assertEquals(false, login.isUscEmail("user@ucla.edu"));
    }
}