package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;

import java.util.HashMap;
import java.util.Map;

public class ShoppingCartDaoMem implements ShoppingCartDao {
    private Map<Integer, Map<Integer, Integer>> shoppingCarts = new HashMap<>();
    private static ShoppingCartDaoMem instance = null;
    private Map<Integer, Integer> sumOfItems = new HashMap<>();


    private ShoppingCartDaoMem() {
    }

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
    public Map<Integer, Integer> getSumOfItems() {
        return sumOfItems;
    }

    @Override
    public void update(int product_id, int quantity) {

        int userId = 1; // CHANGE THIS!

        if (shoppingCarts.containsKey(userId)) {
            sumOfItems.put(userId, sumOfItems.get(userId) + quantity);

            Map<Integer, Integer> userCart = shoppingCarts.get(userId);

            if (userCart.containsKey(product_id)) {
                Integer lastQuantity = userCart.get(product_id);
                userCart.put(product_id, lastQuantity + quantity);
            } else {
                userCart.put(product_id, quantity);
            }

        } else {
            Map<Integer, Integer> addedItem = new HashMap<>();
            addedItem.put(product_id, quantity);
            sumOfItems.put(userId, 1);
            shoppingCarts.put(userId, addedItem);
        }
    }
}
