package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/"})
public class ProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDao productDataStore = ProductDaoMem.getInstance();
        ProductCategoryDao productCategoryDataStore = ProductCategoryDaoMem.getInstance();
        ShoppingCartDao shoppingCarts = ShoppingCartDaoMem.getInstance();

        String itemId = request.getParameter("addToCart");

        if (itemId != null ){
            shoppingCarts.update(Integer.valueOf(itemId),1);
            System.out.println(shoppingCarts.getAll());
            System.err.println(shoppingCarts.getItemsInCarts());
        }

//        Map params = new HashMap<>();
//        params.put("category", productCategoryDataStore.find(1));
//        params.put("products", productDataStore.getBy(productCategoryDataStore.find(1)));

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, response, request.getServletContext());
//        context.setVariables(params);
        int userId = 1;
        context.setVariable("sum_of_items", shoppingCarts.getItemsInCarts().get(userId));
        context.setVariable("recipient", "World");

        context.setVariable("category", productCategoryDataStore.find(1));
        context.setVariable("products", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("categories", productCategoryDataStore.getAll());
        context.setVariable("products", productDataStore.getAll());

        /*
        context.setVariable("products1", productDataStore.getBy(productCategoryDataStore.find(1)));
        context.setVariable("products2", productDataStore.getBy(productCategoryDataStore.find(2)));
        context.setVariable("products3", productDataStore.getBy(productCategoryDataStore.find(3)));
        */

        engine.process("product/index.html", context, response.getWriter());
    }

}
