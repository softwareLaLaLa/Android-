package com.example.lalala.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PaperSimpleData implements Serializable {
    public void setTitle(String title) {
        this.title = title;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public int getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }

    public int getTotalBrowseNum() {
        return totalBrowseNum;
    }

    public void setTotalBrowseNum(int totalBrowseNum) {
        this.totalBrowseNum = totalBrowseNum;
    }

    public void addTag(String tag){
        tagList.add(tag);
    }

    public PaperSimpleData(int paper_id, String title, int totalBrowseNum, List<String> tagList){
        this.paper_id = paper_id;
        this.title = title;
        this.totalBrowseNum = totalBrowseNum;
        this.tagList = tagList;
    }

    public PaperSimpleData(){}

    int paper_id;
    String title;
    int totalBrowseNum;
    List<String> tagList = new ArrayList<>();
}
