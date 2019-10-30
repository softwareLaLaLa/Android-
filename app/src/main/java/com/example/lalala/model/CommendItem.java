package com.example.lalala.model;

import java.util.List;

public class CommendItem {
    String userID;
    String commendContent;
    CommendItem parentComment;
    int likeNum;
    public boolean like;

    public CommendItem(String userID, String commendContent, CommendItem parentComment, int likeNum, boolean like) {
        this.userID = userID;
        this.commendContent = commendContent;
        this.parentComment = parentComment;
        this.likeNum = likeNum;
        this.like = like;
    }

    public String getUserID() {
        return userID;
    }

    public String getCommendContent() {
        return commendContent;
    }

    public void setParentComment(CommendItem parentComment) {
        this.parentComment = parentComment;
    }

    public void addLikeNum() {
        this.likeNum++;
    }

    public void decLikeNum() {
        this.likeNum--;
    }

    public CommendItem getParentComment() {
        return parentComment;
    }

    public int getLikeNum() {
        return likeNum;
    }
}

