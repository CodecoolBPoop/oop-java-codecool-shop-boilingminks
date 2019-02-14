package com.codecool.shop.model;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private String address;
    private String state;
    private String zip;
    private String country;
    private boolean is_admin;

    public User() {
    }

    public boolean is_admin() {
        return is_admin;
    }

    public User(String firstName, String lastName, String email, String hashedPassword, String address, String state, String zip, String country, boolean is_admin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.is_admin = is_admin;
    }

    public User(int id, String firstName, String lastName, String email, String hashedPassword, String address, String state, String zip, String country, boolean is_admin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.hashedPassword = hashedPassword;
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.is_admin = is_admin;
    }

    public int getId() {
        return id;
    }

    public User(String email, String hashedPassword) {
        this.email = email;
        this.hashedPassword = hashedPassword;
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
