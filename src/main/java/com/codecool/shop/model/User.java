package com.codecool.shop.model;

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
}
