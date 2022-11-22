package com.example.homeshare;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class NotificationsAdapterTest {
    @Test
    public void notificationCounter() {
        ArrayList<Object> notifs = new ArrayList<>();
        // Creating 3 notifications for NotificationsAdapter
        for (int i=0; i < 3; i++) {
            Notification notification = new Notification();
            notifs.add(notification);
        }
        NotificationsAdapter na = new NotificationsAdapter(notifs);
        Assert.assertEquals(3, na.getItemCount());
    }
}