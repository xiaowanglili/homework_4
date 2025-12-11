package com.example.homework_4.controller;

import com.example.homework_4.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String inputCaptcha = request.getParameter("captcha");

        String sessionCaptcha = (String) request.getSession().getAttribute("captcha");

        if (sessionCaptcha == null || !sessionCaptcha.equalsIgnoreCase(inputCaptcha)) {
            request.setAttribute("error", "验证码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        ServletContext context = getServletContext();
        Map<String, User> userMap = (Map<String, User>) context.getAttribute("userMap");

        if (userMap == null) {
            request.setAttribute("error", "用户不存在");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            return;
        }

        User user = userMap.get(username);
        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("currentUser", user);
            response.sendRedirect(request.getContextPath() + "/jsp/chat.jsp");
        } else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        }
    }
}