package com.example.homework_4.model;

import java.io.Serializable;

public class User implements Serializable {
    private String name;
    private String pass;

    public User() {}

    public User(String name, String pass) {
        this.name = name;
        this.pass = pass;
    }

    public String getUsername() { return name; }
    public void setUsername(String name) { this.name = name; }

    public String getPassword() { return pass; }
    public void setPassword(String pass) { this.pass = pass; }
}
