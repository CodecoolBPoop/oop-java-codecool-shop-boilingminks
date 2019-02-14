package com.codecool.shop.dao.implementation;

import com.codecool.shop.config.JdbcConnectivity;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;

import java.util.List;

public class OrderDaoJDBC implements OrderDao {

    private static OrderDaoJDBC instance = null;

    private JdbcConnectivity JDBCInstance = JdbcConnectivity.getInstance();

    private OrderDaoJDBC() {
    }

    public static OrderDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Order order) {
        JDBCInstance.executeQuery("INSERT INTO orders (user_id, validated, payment_method)" +
                "VALUES ('" + order.getUser().getId() + "', '" + "true" + "', '" + order.getPaymentMethod() + "');");
    }

    @Override
    public Order find(int id) {
        return null;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Order> getBy(User user) {
        return null;
    }
}
