package com.example.homework_4.listener;

import com.example.homework_4.model.Message;
import com.example.homework_4.model.User;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@WebListener
public class SessionExpireListener implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        ServletContext ctx = se.getSession().getServletContext();

        User user = (User) se.getSession().getAttribute("currentUser");
        if (user == null) return;

        String username = user.getUsername();

        Set<String> onlineUsers = (Set<String>) ctx.getAttribute("onlineUsers");
        if (onlineUsers != null && onlineUsers.remove(username)) {

            List<Message> messages =
                    (List<Message>) ctx.getAttribute("messages");
            if (messages == null) {
                messages = new CopyOnWriteArrayList<>();
                ctx.setAttribute("messages", messages);
            }

            messages.add(new Message("系统消息",
                    username + " 退出聊天室（关闭浏览器或超时）"));
        }
    }
}
