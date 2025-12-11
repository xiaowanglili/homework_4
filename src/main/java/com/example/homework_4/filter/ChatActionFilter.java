package com.example.homework_4.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

@WebFilter("/chat")
public class ChatActionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;

        if ("POST".equalsIgnoreCase(request.getMethod())) {
            String toUser = request.getParameter("toUser");

            if (toUser != null && !toUser.trim().isEmpty()) {
                request.setAttribute("msgType", "private");
                request.setAttribute("msgTarget", toUser.trim());
            } else {
                request.setAttribute("msgType", "public");
            }
        }

        chain.doFilter(req, resp);
    }
}
