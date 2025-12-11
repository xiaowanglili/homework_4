package com.example.homework_4.filter;

import com.example.homework_4.model.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter("/*")
public class UserLogFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        String method = request.getMethod();
        String url = request.getRequestURI();

        // 忽略轮询刷新消息接口（防止刷屏）
        if (url.contains("/chat") && "GET".equals(method)) {
            chain.doFilter(req, resp);
            return;
        }

        String username = "NO_LOGIN";
        if (session != null && session.getAttribute("currentUser") instanceof User) {
            User u = (User) session.getAttribute("currentUser");
            username = u.getUsername();
        }

        System.out.println("[LOG] " + method + " -> " + url + "   user=" + username);

        chain.doFilter(req, resp);
    }
}
