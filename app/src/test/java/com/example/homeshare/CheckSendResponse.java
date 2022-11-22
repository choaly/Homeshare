package com.example.homeshare;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckSendResponse {
    @Test
    public void sendNonEmptyResponse_ReturnsTrue() {
        assertTrue(SendResponse.messageNotEmpty("This is a valid message"));
    }

    @Test
    public void sendEmptyResponse_ReturnsFalse() {
        assertFalse(SendResponse.messageNotEmpty(""));
    }

}
