package com.codecool.shop.controller;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.OrderDaoJDBC;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/payment-validation"})
public class PaymentValidation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OrderDao orderDao = OrderDaoJDBC.getInstance();
        int userId = 1; // TODO: USER SYSTEM!

        HashMap<String, String> checkoutData = new HashMap<>();
        req.getParameterMap().entrySet().stream().forEach(e -> {
            checkoutData.put(e.getKey(), req.getParameter(e.getKey()));
        });
        Transaction tran = new Transaction();
        Order.updateWithCheckout(checkoutData, tran);
        Order currentOrder = Order.currentOrder;
        currentOrder.setTransaction(tran);
        currentOrder.saveToJson();

        try{
            String email = currentOrder.getUser().getEmail();
            String firstName = currentOrder.getUser().getFirstName();

            Emailer.mailTo(email, firstName);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        currentOrder.getShoppingCart().clear();
        ShoppingCartDaoMem.getInstance().getSumOfItems().clear();
        ShoppingCartDaoMem.getInstance().getSumOfItems().put(userId, 0);

        orderDao.add(currentOrder);

        resp.sendRedirect("/");

    }
}
