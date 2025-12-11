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
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

@WebServlet("/chat")
public class ChatServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext ctx = getServletContext();

        if (ctx.getAttribute("messages") == null) {
            ctx.setAttribute("messages", new CopyOnWriteArrayList<Message>());
        }
        if (ctx.getAttribute("onlineUsers") == null) {
            ctx.setAttribute("onlineUsers", Collections.synchronizedSet(new HashSet<String>()));
        }
    }

    /** GET：返回消息区域 + 在线用户区域 HTML 片段 */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html;charset=UTF-8");

        HttpSession session = req.getSession(false);
        User current = (session != null) ? (User) session.getAttribute("currentUser") : null;
        if (current == null) {
            resp.setStatus(401);
            return;
        }
        String currentName = current.getUsername();

        List<Message> messages =
                (List<Message>) getServletContext().getAttribute("messages");
        Set<String> onlineUsers =
                (Set<String>) getServletContext().getAttribute("onlineUsers");

        PrintWriter out = resp.getWriter();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // ====== 消息区域 ======
        out.println("<div id='message-area'>");

        if (messages != null) {
            for (Message msg : messages) {

                boolean isPrivate = msg.getToUser() != null && !msg.getToUser().isEmpty();

                // 私聊：只有双方可见
                if (isPrivate) {
                    boolean isSender = currentName.equals(msg.getSender());
                    boolean isReceiver = currentName.equals(msg.getToUser());
                    if (!isSender && !isReceiver) {
                        continue;
                    }
                }

                out.print("<div class='msg-item");
                if (isPrivate) out.print(" private");
                out.println("'>");

                if (isPrivate) {
                    out.println("<div class='msg-sender'>" +
                            msg.getSender() + " 私聊 → " + msg.getToUser() + "</div>");
                } else {
                    out.println("<div class='msg-sender'>" + msg.getSender() + "</div>");
                }

                out.println("<span class='msg-content'>" + msg.getContent() + "</span>");
                out.println("<div class='msg-time'>" + sdf.format(msg.getTime()) + "</div>");
                out.println("</div>");
            }
        }

        out.println("</div>"); // end message-area

        // ====== 在线用户区域 ======
        out.println("<div id='user-list-area'>");
        out.println("<div class='user-list-title'>在线用户</div>");

        if (onlineUsers != null) {
            synchronized (onlineUsers) {
                for (String u : onlineUsers) {
                    if (!u.equals(currentName)) {
                        out.println("<div class='user-item' onclick=\"startPrivateChat('" + u + "')\">" +
                                u + "</div>");
                    }
                }
            }
        }

        out.println("</div>"); // end user-list-area
    }

    /** POST：发送消息（公共 / 私聊） */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        req.setCharacterEncoding("UTF-8");

        HttpSession session = req.getSession(false);
        User current = (session != null) ? (User) session.getAttribute("currentUser") : null;
        if (current == null) {
            resp.setStatus(401);
            return;
        }

        String content = req.getParameter("content");
        String toUser = req.getParameter("toUser");

        if (content != null && !content.trim().isEmpty()) {
            ServletContext ctx = getServletContext();
            List<Message> messages =
                    (List<Message>) ctx.getAttribute("messages");

            if (messages == null) {
                messages = new CopyOnWriteArrayList<>();
                ctx.setAttribute("messages", messages);
            }

            Message msg = new Message(current.getUsername(), content.trim());
            if (toUser != null && !toUser.trim().isEmpty()) {
                msg.setToUser(toUser.trim());
            }

            messages.add(msg);
        }

        resp.setStatus(200);
    }
}
