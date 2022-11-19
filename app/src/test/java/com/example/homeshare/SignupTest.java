package com.example.homeshare;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.Optional;

public class SignupTest {
    @Test
    public void fieldsFilledIn() {
        Assert.assertEquals(false, Signup.allFieldsFilledIn("", "Password", "Password"));
        Assert.assertEquals(false, Signup.allFieldsFilledIn("email@usc.edu", "", "Password"));
        Assert.assertEquals(false, Signup.allFieldsFilledIn("", "", "Password"));
        Assert.assertEquals(false, Signup.allFieldsFilledIn("Email", "", ""));
        Assert.assertEquals(false, Signup.allFieldsFilledIn("Email", "", "Password"));
        Assert.assertEquals(false, Signup.allFieldsFilledIn("", "", ""));
        Assert.assertEquals(true, Signup.allFieldsFilledIn("email@usc.edu", "Password", "Password"));
        Assert.assertEquals(true, Signup.allFieldsFilledIn("email@usc.edu", "NoMatch", "Password"));
    }

    @Test
    public void passwordsMatch() {
        Assert.assertEquals(true, Signup.passwordsMatch("pass123", "pass123"));
        Assert.assertEquals(false, Signup.passwordsMatch("pass123", "pass"));
    }
}