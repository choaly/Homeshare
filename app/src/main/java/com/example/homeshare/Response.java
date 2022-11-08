package com.example.homeshare;

public class Response {

    String posterId, responderId, posterName, responderName, message;
    boolean accepted;

    public Response(){
    }

    public Response(String posterId, String responderId, String posterName, String responderName, String message, boolean accepted) {
        this.posterId = posterId;
        this.responderId = responderId;
        this.posterName = posterName;
        this.responderName = responderName;
        this.message = message;
        this.accepted = accepted;
    }

    public String getPosterId() {
        return posterId;
    }

    public String getResponderId() {
        return responderId;
    }

    public String getPosterName() {
        return posterName;
    }

    public String getResponderName() {
        return responderName;
    }

    public String getMessage() {
        return message;
    }

    public boolean isAccepted() {
        return accepted;
    }
}