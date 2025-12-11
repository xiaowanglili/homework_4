package com.example.homework_4.controller;

import com.example.homework_4.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        ServletContext context = getServletContext();
        ConcurrentHashMap<String, User> users =
                (ConcurrentHashMap<String, User>) context.getAttribute("userMap");

        if (users == null) {
            users = new ConcurrentHashMap<>();
            context.setAttribute("userMap", users);
        }

        if (users.containsKey(username)) {
            req.setAttribute("error", "用户名已存在");
            req.getRequestDispatcher("/jsp/register.jsp").forward(req, resp);
            return;
        }

        users.put(username, new User(username, password));
        resp.sendRedirect(req.getContextPath() + "/jsp/login.jsp");
    }
}