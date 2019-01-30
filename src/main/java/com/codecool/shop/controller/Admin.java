package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.dao.implementation.SupplierDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ShoppingCartDao shoppingCarts = ShoppingCartDaoMem.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
        SupplierDao suppliers = SupplierDaoMem.getInstance();
        ProductCategoryDao categories = ProductCategoryDaoMem.getInstance();

        int userId = 1; // TODO : USER SYSTEM!
        context.setVariable("suppliers", suppliers.getAll());
        context.setVariable("categories", categories.getAll());
        context.setVariable("sum_of_items", shoppingCarts.getSumOfItems().get(userId));
        context.setVariable("userID", userId);
        engine.process("admin.html", context, response.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        WebContext context = new WebContext(request, response, request.getServletContext());

        HashMap<String, String> postData = new HashMap<>();
        request.getParameterMap().entrySet().stream().forEach(e -> {
            postData.put(e.getKey(), request.getParameter(e.getKey()));
        });

        System.out.println(postData.toString());


        int userId = 1; // TODO: USER SYSTEM
        context.setVariable("userID", userId);

        response.sendRedirect("/admin");

    }

}
