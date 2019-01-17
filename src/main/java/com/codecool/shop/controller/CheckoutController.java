package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/checkout")
public class CheckoutController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        SupplierDao supplierDataStore = SupplierDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        engine.process("product/checkout.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");
        String address = req.getParameter("address");
        String address2 = req.getParameter("address2");
        String state = req.getParameter("state");
        String zip = req.getParameter("zip");
        String country = req.getParameter("country");
        String shipping = req.getParameter("shipping");
        String saveInfo = req.getParameter("saveInfo");
        HashMap<String, String> checkoutData = new HashMap<>();
        checkoutData.put("firstName", firstName);
        checkoutData.put("lastName", lastName);
        checkoutData.put("email", email);
        checkoutData.put("address", address);
        checkoutData.put("address2", address2);
        checkoutData.put("state", state);
        checkoutData.put("zip", zip);
        checkoutData.put("country", country);
        checkoutData.put("shipping", shipping);
        checkoutData.put("saveInfo", saveInfo);
        User user = new User();
        user.updateWithCheckout(checkoutData);
        Order order = new Order();
        order.setUser(user);
        resp.sendRedirect("/payment-choice");

    }










}


