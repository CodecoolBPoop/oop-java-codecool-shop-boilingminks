package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductCategoryDao;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductCategoryDaoMem;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = {"/shoppingcart"})
public class ShoppingCartHTML extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();
        ProductDao productDataStore = ProductDaoMem.getInstance();

        Map <Product, Integer> cartData = new HashMap<>();

        String stringItemId = request.getParameter("changeCart");



        int userId = 1;
        Map<Integer, Integer> userCart = shoppingCartDao.getAll().get(userId);

        if (stringItemId != null ){

            Integer itemId = Integer.valueOf(stringItemId);
            if (itemId < 0){
                itemId = Math.abs(itemId);
                shoppingCartDao.update(itemId, -1);
                if (userCart.get(itemId) < 1){
                    userCart.remove(itemId);
                }
            }
            else{
                userCart.put(itemId, userCart.get(itemId) + 1);
            }
        }


        for (Integer key : userCart.keySet()) {
            cartData.put(productDataStore.find(key), userCart.get(key) );
        }

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext context = new WebContext(request, resp, request.getServletContext());



       context.setVariable("cart_data", cartData);

        engine.process("product/shoppingcart.html", context, resp.getWriter());



    }

}