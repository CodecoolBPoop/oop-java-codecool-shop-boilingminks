package com.codecool.shop.shoppingCart;

import com.codecool.shop.model.Product;

import java.util.HashMap;
import java.util.Map;

public class shoppingCart {
    private static Map<Integer, Integer> shoppingCart = new HashMap<>();

    public static Map<Integer, Integer> getShoppingCart() {
        return shoppingCart;
    }

    public static void changeShoppingCart(int id, int quantity) {

        if (shoppingCart.containsKey(id)){
            shoppingCart.put(id, shoppingCart.get(id) + quantity);
        }
        else{
            shoppingCart.put(id, quantity);
        }
    }
}
