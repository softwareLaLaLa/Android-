package com.example.lalala.entity;

import java.util.ArrayList;
import java.util.List;

public class UserInfor {
    private int usr_id;
    private String name;
    private String avatar;
    private List<PaperSimpleData> browseHistory = new ArrayList<>();


    private List<Integer> groupID = new ArrayList<>();
    private List<Integer> candidateGroupID = new ArrayList<>();

    public UserInfor() {
        usr_id = 1;
        name = "username";
        avatar = "student";
    }

    public List<Integer> getGroupID() {
        return groupID;
    }

    public void setGroupID(List<Integer> groupID) {
        this.groupID = groupID;
    }

    public List<Integer> getCandidateGroupID() {
        return candidateGroupID;
    }


    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setBrowseHistory(List<PaperSimpleData> browseHistory) {
        this.browseHistory = browseHistory;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public String getName() {
        return name;
    }


    public List<PaperSimpleData> getBrowseHistory() {
        return browseHistory;
    }


}
