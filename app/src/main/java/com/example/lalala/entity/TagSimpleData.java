package com.example.lalala.entity;

public class TagSimpleData {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TagSimpleData(int tagId, String tagName){
        this.id=tagId;
        this.name=tagName;
    }


}
