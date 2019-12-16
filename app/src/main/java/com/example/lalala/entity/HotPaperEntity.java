package com.example.lalala.entity;

import java.util.Date;
public class HotPaperEntity {
    private int id;

    private int hot;

    private Date lastActiveTime;

    public void setHot(int hot) {
        this.hot = hot;
    }

    public void setLastActiveTime(Date lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public int getHot() {
        return hot;
    }

    public Date getLastActiveTime() {
        return lastActiveTime;
    }

    public int getId() {
        return id;
    }

    public HotPaperEntity(int hot, Date lastActiveTime) {
        this.hot = hot;
        this.lastActiveTime = lastActiveTime;
    }

    public HotPaperEntity(){}

    public void setId(int id) {
        this.id = id;
    }
}
