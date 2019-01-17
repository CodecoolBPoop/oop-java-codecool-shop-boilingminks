package com.codecool.shop.model;


import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.google.gson.Gson;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private int id;
    private User user;
    private Map<Integer, Integer> shoppingCart;

    public Order() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setShoppingCart(int userId){
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        shoppingCart = shoppingCartDao.getAll().get(userId);
    }

    public void saveToJson() throws IOException {
        String filepath = "./src/main/webapp/json/data.json";
        Gson gson = new Gson();
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(gson.toJson(this));
        writer.close();
    }

}
