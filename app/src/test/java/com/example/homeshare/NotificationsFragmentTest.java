package com.example.homeshare;

import org.junit.Assert;
import org.junit.Test;

public class NotificationsFragmentTest {
    @Test
    public void isMatchedNotification() {
        Assert.assertEquals(true, NotificationsFragment.isMatchedNotification("You've been matched!"));
        Assert.assertEquals(false, NotificationsFragment.isMatchedNotification("You have a new response!"));
    }

    @Test
    public void isResponseNotification() {
        Assert.assertEquals(false, NotificationsFragment.isResponseNotification("You've been matched!"));
        Assert.assertEquals(true, NotificationsFragment.isResponseNotification("You have a new response!"));
    }
}