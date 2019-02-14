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
        System.out.println(order.getShoppingCart().toString());
        JDBCInstance.executeQuery("INSERT INTO orders (id, user_id, validated, payment_method)" +
                "VALUES ('" + order.getId() + "', '" + order.getUser().getId() + "', '" + "true" + "', '" + order.getPaymentMethod() + "');");
        System.out.println(order.getId());
        for(Integer key : order.getShoppingCart().keySet()) {
            JDBCInstance.executeQuery("INSERT INTO shopping_cart (order_id, product_id, quantity)" +
            "VALUES ('" + order.getId() + "','" + key + "','" + order.getShoppingCart().get(key) + "');");
        }

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
