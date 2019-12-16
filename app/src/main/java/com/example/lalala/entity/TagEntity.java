package com.example.lalala.entity;

import java.util.Date;
public class TagEntity {
    private int id;

    private String name;

    private String groupIDList;

    private int num;

    private Date date;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public Date getDate() {
        return date;
    }

    public String getGroupIDList() {
        return groupIDList;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public TagEntity(String name, String groupIDList, int num, Date date) {
        this.name = name;
        this.groupIDList = groupIDList;
        this.num = num;
        this.date = date;
    }

    public TagEntity(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroupIDList(String groupIDList) {
        this.groupIDList = groupIDList;
    }
}
