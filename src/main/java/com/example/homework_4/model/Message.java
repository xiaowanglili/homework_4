package com.example.homework_4.model;

import java.util.Date;

public class Message {
    private String sender;
    private String content;
    private Date time;

    public Message(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.time = new Date();
    }

    public String getSender() { return sender; }
    public String getContent() { return content; }
    public Date getTime() { return time; }
}
