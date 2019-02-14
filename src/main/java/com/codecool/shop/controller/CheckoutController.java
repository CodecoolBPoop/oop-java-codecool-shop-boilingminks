package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/checkout")
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = UserDaoJDBC.getInstance();

        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        User testUser = new User(firstName, lastName, email, "notyet", address, state, zip, country);

        // USER CREATION IN SQL (needs userdao)
        try {

            // BOOLEAN IN STATEMENT IS A PRIVATE METHOD, MOVE IT AS WELL
            if (isExistingEmail(email)) {
                System.err.println("This email already exists!");
            }
        } catch (IllegalArgumentException e) {
            userDao.add(testUser);
        }
        // END OF USER CREATION

        HashMap<String, String> checkoutData = new HashMap<>();
        checkoutData.put("firstName", firstName);
        checkoutData.put("lastName", lastName);
        checkoutData.put("email", email);
        checkoutData.put("address", address);
        checkoutData.put("state", state);
        checkoutData.put("zip", zip);
        checkoutData.put("country", country);
        User user = new User();
        Order.updateWithCheckout(checkoutData, user);
        Order.currentOrder = new Order();
        Order.currentOrder.setUser(user);
        Order.currentOrder.setShoppingCart(1);
        resp.sendRedirect("/payment-choice");
    }

    private boolean isExistingEmail(String email) {
        UserDao userDao = UserDaoJDBC.getInstance();
        String email1 = userDao.findByEmail(email).getEmail();
        return email.equals(email1);
    }


}


