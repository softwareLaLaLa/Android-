package com.example.lalala.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class UserInfor {
    int usr_id;
    String name;
    String avatar;
    List<PaperSimpleData> browseHistory = new ArrayList<>();
    List<Integer> groupID = new ArrayList<>();
    List<Integer> candidateGroupID = new ArrayList<>();

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

    public UserInfor(UserEntity userEntity, List<PaperSimpleData> browseHistory){
        Gson gson = new Gson();
        this.usr_id = userEntity.getId();
        this.name = userEntity.getName();
        this.avatar = userEntity.getAvatar();
        this.browseHistory = browseHistory;
        Type idListType = new TypeToken<ArrayList<Integer>>(){}.getType();
        this.groupID = gson.fromJson(userEntity.getGroup(), idListType);
        this.candidateGroupID = gson.fromJson(userEntity.getCandidateGroup(), idListType);
    }
}
