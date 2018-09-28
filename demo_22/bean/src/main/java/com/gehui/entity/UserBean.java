package com.gehui.entity;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/9/27 16:55.
 **/
public class UserBean implements Serializable {
    private String username;
    private String password;

    public UserBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserBean() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
