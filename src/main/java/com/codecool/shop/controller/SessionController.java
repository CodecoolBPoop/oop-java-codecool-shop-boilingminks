package com.codecool.shop.controller;

import com.codecool.shop.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class SessionController {

    public static void createSession(HttpServletRequest req, String email, User user) {
        HttpSession session = req.getSession(true);
        session.setAttribute("email", email);
        session.setAttribute("userIsAdmin", user.is_admin());
    }

    public static void destroySession(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
    }

}
