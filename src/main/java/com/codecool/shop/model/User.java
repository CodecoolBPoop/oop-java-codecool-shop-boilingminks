package com.codecool.shop.model;

public class User {

    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private String address;
    private String state;
    private String zip;
    private String country;

    public User() {
    }

    public User(String firstName, String lastName, String email, String hashedPassword, String address, String state, String zip, String country) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getAddress() {
        return address;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public String toString() {
        return getEmail() + " " + getFirstName() + " " + getLastName();
    }
}
