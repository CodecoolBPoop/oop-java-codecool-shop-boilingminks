package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/filter"})
public class ProductFilter extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductDao products = ProductDaoMem.getInstance();
        ProductCategoryDao productCategories = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCarts = ShoppingCartDaoMem.getInstance();

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        String parameter = req.getParameter("cat");
        System.out.printf("The request I asked for is %s%n", parameter);

        int id = parameter.equals("Tablet") ? 1 : parameter.equals("Book") ? 2 : 3;


        int userId = 1;
        context.setVariable("sum_of_items", shoppingCarts.getItemsInCarts().get(userId));
        context.setVariable("recipient", "World");
        context.setVariable("categories", productCategories.getAll());
        context.setVariable("products", products.getBy(productCategories.find(id)));

        engine.process("product/index.html", context, resp.getWriter());



    }

}
