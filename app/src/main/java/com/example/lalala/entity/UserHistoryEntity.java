package com.example.lalala.entity;

import java.util.Date;

public class UserHistoryEntity {
    private int userId;
    private int paperId;
    private Date browseTime;
    private boolean uncheck;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPaperId() {
        return paperId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public Date getBrowseTime() {
        return browseTime;
    }

    public void setBrowseTime(Date browseTime) {
        this.browseTime = browseTime;
    }

    public boolean isUncheck() {
        return uncheck;
    }

    public void setUncheck(boolean uncheck) {
        this.uncheck = uncheck;
    }
}
