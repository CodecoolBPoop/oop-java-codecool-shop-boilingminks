package com.codecool.shop.model;


import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    public static Order currentOrder;
    private int id;
    private User user;
    private Transaction transaction;


    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void updateWithCheckout(HashMap<String, String> checkoutData, Object updatedClass) {
        checkoutData.entrySet().stream().forEach(e -> {
            try {
                Field currentField = updatedClass.getClass().getDeclaredField(e.getKey());
                boolean accessible = currentField.isAccessible();
                currentField.setAccessible(true);
                currentField.set(updatedClass, e.getValue());
                currentField.setAccessible(accessible);
            } catch (NoSuchFieldException | IllegalAccessException e1) {
                e1.printStackTrace();
            }
        });
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void saveToJson() throws IOException {
        String filepath = "./src/main/webapp/json/data.json";
        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(gson.toJson(this));
        writer.close();
    }

    public void setTransaction(Transaction tran) { this.transaction = tran; }
}
