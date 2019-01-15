package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private Map<Integer, Map<Integer, Integer>> shoppingCarts = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem(){}

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }

    @Override
    public Map<Integer, Map<Integer, Integer>> getAll() {
        return shoppingCarts;
    }

    @Override
    public void update(int id, int quantity) {

        int userID = 1; // CHANGE THIS!

        if (shoppingCarts.containsKey(userID)){

            Map<Integer, Integer> userCart = shoppingCarts.get(userID);

            if(userCart.containsKey(id)){
                Integer lastQuantity = userCart.get(id);
                userCart.put(id, lastQuantity + quantity);
            }
            else {
                userCart.put(id, quantity);
            }

        }
        else{
            Map<Integer, Integer> addedItem = new HashMap<>();
            addedItem.put(id, quantity);
            shoppingCarts.put(userID, addedItem);
        }
    }
}
