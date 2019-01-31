package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.dao.implementation.*;
import com.codecool.shop.model.Product;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/admin"})
public class Admin extends HttpServlet {

    private SupplierDao suppliers = SupplierDaoMem.getInstance();
    private ProductCategoryDao categories = ProductCategoryDaoMem.getInstance();
    private ShoppingCartDao shoppingCarts = ShoppingCartDaoMem.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());

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
        int userId = 1; // TODO: USER SYSTEM

        Product newProduct = new Product(request.getParameter("productName"),
                Float.parseFloat(request.getParameter("price")), "USD",
                request.getParameter("description"),
                categories.find(Integer.parseInt(request.getParameter("productCategory"))),
                suppliers.find(Integer.parseInt(request.getParameter("supplier"))),
                request.getParameter("picture"));

//        ProductDaoJDBC.getInstance().add(newProduct);
        ProductDaoMem.getInstance().add(newProduct);

        context.setVariable("userID", userId);
        response.sendRedirect("/admin");

    }
}
