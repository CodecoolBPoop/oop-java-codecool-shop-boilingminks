package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoJDBC;
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
import java.util.Map;

@WebServlet(urlPatterns = {"/shoppingcart"})
public class ShoppingCartController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {
        ShoppingCartDao shoppingCarts = ShoppingCartDaoMem.getInstance();
        ProductDao productDao = ProductDaoJDBC.getInstance();

        Map<Product, Integer> cartData = new HashMap<>();

        String stringItemId = request.getParameter("changeCart");

        int userId = 1; // TODO: USER SYSTEM
        float sumOfPrices = 0;
        Map<Integer, Integer> userCart = shoppingCarts.getAll().get(userId);
        System.out.println(userCart);
        if (stringItemId != null) {

            updateCart(userId, shoppingCarts, stringItemId, userCart);
        }
        if (userCart != null) {

            for (Integer key : userCart.keySet()) {
                Integer quant = userCart.get(key);
                cartData.put(productDao.find(key), quant);
                sumOfPrices += productDao.find(key).getDefaultPrice() * quant;
            }

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, resp, request.getServletContext());


            context.setVariable("cart_data", cartData);
            context.setVariable("sum_of_prices", sumOfPrices);
            context.setVariable("sum_of_items", shoppingCarts.getSumOfItems().get(userId));
            engine.process("product/shoppingcart.html", context, resp.getWriter());
        } else {

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
            WebContext context = new WebContext(request, resp, request.getServletContext());
            engine.process("product/shoppingcart.html", context, resp.getWriter());

        }


    }

    public static void updateCart(int userId, ShoppingCartDao shoppingCartDao, String stringItemId, Map<Integer, Integer> userCart) {
        Integer itemId = Integer.valueOf(stringItemId);
        if (itemId < 0) {
            itemId = Math.abs(itemId);
            shoppingCartDao.update(userId, itemId, -1);
            if (userCart.get(itemId) < 1) {
                userCart.remove(itemId);
            }
        } else {
            shoppingCartDao.update(userId, itemId, 1);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ShoppingCartDao shoppingCartDao = ShoppingCartDaoMem.getInstance();

        String stringItemId = req.getParameter("changeCart");
        int userId = 1; // TODO: USER SYSTEM

        Map<Integer, Integer> userCart = shoppingCartDao.getAll().get(userId);

        if (stringItemId != null) {
            ShoppingCartController.updateCart(userId, shoppingCartDao, stringItemId, userCart);
        }

        resp.sendRedirect("/shoppingcart");
    }
}