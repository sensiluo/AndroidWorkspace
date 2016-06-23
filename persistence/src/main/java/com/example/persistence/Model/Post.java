package com.example.persistence.Model;

/**
 * Created by H_P on 2016/4/28.
 * @author luo
 * @version 1.0
 */
public class Post {
    private User user;
    private String text;

    public String getText() {
        return text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setText(String text) {
        this.text = text;
    }
}
