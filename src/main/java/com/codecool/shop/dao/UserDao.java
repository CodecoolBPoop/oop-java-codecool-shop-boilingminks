package com.codecool.shop.dao;

import com.codecool.shop.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserDao {

    void add(User user);

    User findByEmail(String email);

    boolean isExistingEmail(String email);

    void remove(int id);

    List<User> getAll();

    void updateUserTable(int userId, User user);
}
