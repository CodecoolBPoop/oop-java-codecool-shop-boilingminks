package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserDaoJDBC implements UserDao {
    private static UserDaoJDBC instance = null;

    private JdbcConnectivity JDBCInstance = JdbcConnectivity.getInstance();

    private UserDaoJDBC() {
    }

    public static UserDaoJDBC getInstance() {
        if (instance == null) {
            instance = new UserDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(User user) {
        JDBCInstance.executeQuery("INSERT INTO users (first_name, last_name, email, hashed_password, address, state, zip, country, is_admin)" +
                "VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmail() + "', '" + user.getHashedPassword() + "', '" + user.getAddress() + "','" + user.getState() + "', '" + user.getZip() + "', '" + user.getCountry() + "', '" + "false" + "');");
    }

    @Override
    public User findByEmail(String email) {
        List<HashMap<String, String>> hashMaps = JDBCInstance.executeQuerySelect("SELECT * FROM users WHERE email= '" + email + "' ;");
        List<User> users = getUserListFromHashMap(hashMaps);
        if (users.isEmpty()) {
            throw new IllegalArgumentException("email isn't found!");
        } else {
            return users.get(0);
        }
    }

    @Override
    public boolean isExistingEmail(String email) {
        try {
            instance.findByEmail(email);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public void updateUserTable(int userId, HashMap<String, String> user) {
        String stringUserId = String.valueOf(userId);

        JDBCInstance.executeQuery("UPDATE users SET " +
                "first_name = '" + user.get("firstName") + "', '" +
                "'last_name = '" + user.get("lastName") + "', '" +
                "'address = '" + user.get("address") + ", '" +
                "'state = '" + user.get("state") + "', '" +
                "'zip = '" + user.get("zip") + "', '" +
                "'country = '" + user.get("country") + "' '" +
                "'WHERE id = '" + stringUserId + "';");
    }


    private List<User> getUserListFromHashMap(List<HashMap<String, String>> hashMaps) {
        List<User> resultList = new ArrayList<>();
        for (HashMap<String, String> hashMap : hashMaps) {
            boolean isAdmin = false;
            if (hashMap.get("is_admin").equals("t")) {
                isAdmin = true;
            }
            User user = new User(Integer.parseInt(hashMap.get("id")), hashMap.get("first_name"), hashMap.get("last_name"), hashMap.get("email"),
                    hashMap.get("hashed_password"), hashMap.get("address"), hashMap.get("state"),
                    hashMap.get("zip"), hashMap.get("country"), isAdmin);
            resultList.add(user);
        }
        return resultList;
    }


}
