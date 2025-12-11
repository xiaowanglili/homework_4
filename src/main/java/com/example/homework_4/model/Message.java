package com.example.homework_4.model;

import java.util.Date;

public class Message {

    private String sender;    // 发送者
    private String content;   // 消息内容
    private String toUser;    // 私聊目标(null 表示公共)
    private Date time;        // 发送时间

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.time = new Date();
    }

    public String getSender() {
        return sender;
    }

    public String getContent() {
        return content;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Date getTime() {
        return time;
    }
}
