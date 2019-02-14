package com.codecool.shop.model;


import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Order {
    public static Order currentOrder;
    private static int uniqId = 1;
    private int id = uniqId++;
    private User user;
    private Transaction transaction;
    private PaymentMethod paymentMethod;

    private Map<Integer, Integer> shoppingCart;

    public Order() {
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

//    public void setId(int id) {
//        this.id = id;
//    }

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

    public String getPaymentMethod() {
        return paymentMethod.toString();
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShoppingCartToUser(int userId) {
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        shoppingCart = shoppingCartDao.getAll().get(userId);
    }

    public void setShoppingCart(Map<Integer, Integer> shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public void saveToJson() throws IOException {
        String filepath = "./src/main/webapp/json/data.json";
        File jsonFile = new File("./src/main/webapp/json/data.json");
        jsonFile.createNewFile();
        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(gson.toJson(this));
        writer.close();
    }

    public void setTransaction(Transaction tran) {
        this.transaction = tran;
        this.paymentMethod = tran.getPaymentMethod();
    }

    public Map<Integer, Integer> getShoppingCart() {
        return shoppingCart;
    }
}
