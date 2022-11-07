package com.example.homeshare;

public class User {
    String firstName, lastName, email, gender, year, bio;
    public User() {}

    public User(String firstName, String lastName, String email, String gender, String year, String bio) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.year = year;
        this.bio = bio;
    }

    public String getFirstName() {return firstName;}
    public String getLastName() {return lastName;}
    public String getEmail() {return email;}
    public String getGender() {return gender;}
    public String getYear() {return year;}
    public String getBio() {return bio;}
}
