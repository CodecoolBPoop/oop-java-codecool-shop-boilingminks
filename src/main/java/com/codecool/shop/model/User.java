package com.codecool.shop.model;

import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class User {
    private static int id;
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
        this.id = id;
        id++;
    }

}
