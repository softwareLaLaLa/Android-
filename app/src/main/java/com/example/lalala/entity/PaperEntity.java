package com.example.lalala.entity;

public class PaperEntity {
    private int id;
    private String title;
    private String abst;
    private Integer browseNum;
    private Integer recentBrowseNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getRecentBrowseNum() {
        return recentBrowseNum;
    }

    public void setRecentBrowseNum(Integer recentBrowseNum) {
        this.recentBrowseNum = recentBrowseNum;
    }
}
