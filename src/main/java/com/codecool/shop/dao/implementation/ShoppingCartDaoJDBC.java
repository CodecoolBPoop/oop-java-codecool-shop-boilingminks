package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;

import java.util.Map;

public class ShoppingCartDaoJDBC implements ShoppingCartDao {
    @Override
    public void update(int id, int quantity) {

    }

    @Override
    public Map<Integer, Map<Integer, Integer>> getAll() {
        return null;
    }

    @Override
    public Map<Integer, Integer> getSumOfItems() {
        return null;
    }
}
