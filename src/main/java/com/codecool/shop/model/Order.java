package com.codecool.shop.model;


import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private int id;
    private int userId;
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


    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void updateWithCheckout(HashMap<String, String> checkoutData) {
        this.firstName = checkoutData.get("firstName");
        this.lastName = checkoutData.get("lastName");
        this.email = checkoutData.get("email");
        this.address = checkoutData.get("address");
        this.address2 = checkoutData.get("address2");
        this.state = checkoutData.get("state");
        this.zip = checkoutData.get("zip");
        this.country = checkoutData.get("country");
        this.shipping = checkoutData.get("shipping");
        this.saveInfo = checkoutData.get("saveInfo");
    }

    public void saveToJson() throws IOException {
        String filepath = "./src/main/webapp/json/data.json";
        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(gson.toJson(this));
        writer.close();
    }

}
