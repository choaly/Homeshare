package com.example.homeshare;

public class User {
    String firstName, lastName, gender, year, bio;
    public User() {}

    public User(String firstName, String lastName, String gender, String year, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.year = year;
        this.bio = bio;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getGender() {return gender;}
    public String getYear() {return year;}
    public String getBio() {return bio;}
}
