package com.example.homework_4.controller;

import com.example.homework_4.model.Message;
import com.example.homework_4.model.User;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();
        if (ctx.getAttribute("messages") == null) {
            ctx.setAttribute("messages", new CopyOnWriteArrayList<Message>());
        }
    }

    /** GET：返回消息 HTML 片段 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=UTF-8");

        List<Message> messages =
                (List<Message>) getServletContext().getAttribute("messages");

        PrintWriter out = resp.getWriter();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        for (Message msg : messages) {
            out.println("<div class='msg'>");
            out.println("<span class='sender'>" + msg.getSender() + "</span> ");
            out.println("<span class='content'>" + msg.getContent() + "</span>");
            out.println("<div class='time'>" + sdf.format(msg.getTime()) + "</div>");
            out.println("</div>");
        }
    }

    /** POST：发送消息（无跳转） */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");

        User user = (User) req.getSession().getAttribute("currentUser");
        if (user == null) {
            resp.sendError(401, "未登录");
            return;
        }

        String content = req.getParameter("content");

        if (content != null && !content.trim().isEmpty()) {
            List<Message> messages =
                    (List<Message>) getServletContext().getAttribute("messages");

            messages.add(new Message(user.getUsername(), content.trim()));
        }

        resp.setStatus(200);
    }
}
