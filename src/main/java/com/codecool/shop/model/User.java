package com.codecool.shop.model;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class User {
    private final int id = 1; // TODO: USER SYSTEM!
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String address2;
    private String state;
    private String zip;
    private String country;
    private String shipping;
    private String saveInfo;

    public User() {
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }
}
