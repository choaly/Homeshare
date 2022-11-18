package com.example.homeshare;

public class MatchedNotif extends Notification{
    String matchedName, contact;

    public MatchedNotif(){

    }

    public MatchedNotif(String matchedName, String contact) {
        this.matchedName = matchedName;
        this.contact = contact;
    }

    public String getMatchedName() {
        return matchedName;
    }

    public String getContact() {
        return contact;
    }
}
