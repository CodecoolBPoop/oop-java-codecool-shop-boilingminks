package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.Transaction;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/payment-validation"})
public class PaymentValidation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = 1; // TODO: USER SYSTEM!

        HashMap<String, String> checkoutData = new HashMap<>();
        req.getParameterMap().entrySet().stream().forEach(e -> {
            checkoutData.put(e.getKey(), req.getParameter(e.getKey()));
        });
        Transaction tran = new Transaction();
        Order.updateWithCheckout(checkoutData, tran);
        Order.currentOrder.setTransaction(tran);
        Order.currentOrder.saveToJson();

        try{
            String email = Order.currentOrder.getUser().getEmail();
            String firstName = Order.currentOrder.getUser().getFirstName();

            Emailer.mailTo(email, firstName);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Order.currentOrder.getShoppingCart().clear();
        ShoppingCartDaoMem.getInstance().getSumOfItems().clear();
        ShoppingCartDaoMem.getInstance().getSumOfItems().put(userId, 0);

        resp.sendRedirect("/");

    }
}
