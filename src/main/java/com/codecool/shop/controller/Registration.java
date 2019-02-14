package com.codecool.shop.controller;


import com.codecool.shop.dao.UserDao;
import com.codecool.shop.dao.implementation.UserDaoJDBC;
import com.codecool.shop.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = {"/registration"})
public class Registration extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String emailAddress = req.getParameter("email");
        String password = req.getParameter("password");
        User tempUser = new User(emailAddress, password);
        UserDao userdao = UserDaoJDBC.getInstance();

        if(userdao.isExistingEmail(emailAddress)){
           resp.sendRedirect("/");
        }else{
            userdao.add(tempUser);
            SessionController.createSession(req, emailAddress, tempUser);
            resp.sendRedirect("/");
        }
        resp.sendRedirect("/");
    }
}
