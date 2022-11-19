package com.example.homeshare;

import org.junit.Assert;
import org.junit.Test;

public class WelcomePageTest {
    @Test
    public void allFilledIn() {
        Assert.assertEquals(true, WelcomePage.allFieldsFilledOut("Tommy", "Trojan", "Male", "Junior", "Hi I am Tommy"));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("", "Trojan", "Male", "Junior", "Hi I am Tommy"));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("Tommy", "", "Male", "Junior", "Hi I am Tommy"));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("Tommy", "Trojan", "", "Junior", "Hi I am Tommy"));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("Tommy", "Trojan", "Male", "", "Hi I am Tommy"));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("Tommy", "Trojan", "Male", "Junior", ""));
        Assert.assertEquals(false, WelcomePage.allFieldsFilledOut("", "", "", "", ""));
    }
}