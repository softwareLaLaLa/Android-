package com.example.lalala.entity;

public class UserSubscribeData {
    int userId;
    String userName;
    boolean subscribe;

    public UserSubscribeData(int userId, boolean subscribe) {
        this.userId = userId;
        this.subscribe = subscribe;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isSubscribe() {
        return subscribe;
    }

    public void setSubscribe(boolean subscribe) {
        this.subscribe = subscribe;
    }
}
