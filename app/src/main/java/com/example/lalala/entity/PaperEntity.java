package com.example.lalala.entity;

public class PaperEntity {
    private int id;

    private String title;

    private String abst;

    private String resUrl;

    //用户浏览次数
    private int browseNum;

    //用户添加tag次数
    private int evalNum;

    //未check数据次数
    private int uncheckNum;

    public int getUncheckNum() {
        return uncheckNum;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAbst() {
        return abst;
    }

    public String getResUrl() {
        return resUrl;
    }

    public int getBrowseNum() {
        return browseNum;
    }

    public int getEvalNum() {
        return evalNum;
    }

    public void setBrowseNum(int browseNum) {
        this.browseNum = browseNum;
    }

    public void setEvalNum(int evalNum) {
        this.evalNum = evalNum;
    }

    public void setUncheckNum(int uncheckNum) {
        this.uncheckNum = uncheckNum;
    }

    public PaperEntity(String title, String abst, String resUrl, int browseNum, int evalNum, int uncheckNum) {
        this.title = title;
        this.abst = abst;
        this.resUrl = resUrl;
        this.browseNum = browseNum;
        this.evalNum = evalNum;
        this.uncheckNum = uncheckNum;
    }

    public PaperEntity(){}
}
