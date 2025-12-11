package com.example.homework_4.filter;

import com.example.homework_4.model.Message;
import com.example.homework_4.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@WebFilter("/chat")
public class UserEnterExitFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(false);

        if (session != null) {
            User user = (User) session.getAttribute("currentUser");
            if (user != null) {
                String username = user.getUsername();
                ServletContext ctx = request.getServletContext();

                Set<String> onlineUsers =
                        (Set<String>) ctx.getAttribute("onlineUsers");
                if (onlineUsers == null) {
                    onlineUsers = Collections.synchronizedSet(new HashSet<>());
                    ctx.setAttribute("onlineUsers", onlineUsers);
                }

                List<Message> messages =
                        (List<Message>) ctx.getAttribute("messages");
                if (messages == null) {
                    messages = new CopyOnWriteArrayList<>();
                    ctx.setAttribute("messages", messages);
                }

                // 首次加入聊天室
                if (!onlineUsers.contains(username)) {
                    onlineUsers.add(username);
                    messages.add(new Message("系统消息",
                            username + " 进入聊天室"));
                }
            }
        }

        chain.doFilter(req, resp);
    }
}
