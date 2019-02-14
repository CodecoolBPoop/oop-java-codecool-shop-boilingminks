package com.codecool.shop.dao;

import java.util.Map;

public interface ShoppingCartDao {

    void update(int userId, int productId, int quantity);

    Map<Integer, Map<Integer, Integer>> getAll();
    Map<Integer, Integer> getSumOfItems();

}
