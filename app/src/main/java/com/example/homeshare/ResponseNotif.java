package com.example.homeshare;

public class ResponseNotif extends Notification{
    String responderName, postTitle;

    public ResponseNotif(){

    }

    public ResponseNotif(String responderName, String postTitle) {
        this.responderName = responderName;
        this.postTitle = postTitle;
    }

    public String getResponderName() {
        return responderName;
    }

    public String getPostTitle() {
        return postTitle;
    }
}
