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

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("error");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("email");
        String password = req.getParameter("password");

        UserDao userdao = UserDaoJDBC.getInstance();

        try {
            User user = userdao.findByEmail(username);

            if(password.equals(user.getHashedPassword())){
                HttpSession session = req.getSession(true);

                session.setAttribute("email", username);
                session.setAttribute("userIsAdmin", user.is_admin());
            }else {
                System.out.println("TRY AGAIN!");
            }


        }catch (IllegalArgumentException e){
            e.printStackTrace();
        }

        resp.sendRedirect("/");

    }
}
