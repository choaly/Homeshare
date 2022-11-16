package com.example.homeshare;

public class ResponseNotif extends Notification{
    String responseKey, responderName, postTitle;

    public ResponseNotif(){
    }

    public ResponseNotif(String responseKey, String responderName, String postTitle) {
        this.responseKey = responseKey;
        this.responderName = responderName;
        this.postTitle = postTitle;
    }

    public String getResponseKey() {
        return responseKey;
    }

    public String getResponderName() {
        return responderName;
    }

    public String getPostTitle() {
        return postTitle;
    }
}
