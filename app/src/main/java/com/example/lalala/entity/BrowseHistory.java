package com.example.lalala.entity;

import java.util.List;

public class BrowseHistory {
    int usr_id;
    List<PaperSimpleData> browsePaperData;

    public List<PaperSimpleData> getBrowsePaperData() {
        return browsePaperData;
    }

    public int getUsr_id() {
        return usr_id;
    }

    public void setBrowsePaperData(List<PaperSimpleData> browsePaperData) {
        this.browsePaperData = browsePaperData;
    }

    public void setUsr_id(int usr_id) {
        this.usr_id = usr_id;
    }

    public BrowseHistory(int usr_id, List<PaperSimpleData> browsePaperData) {
        this.browsePaperData = browsePaperData;
        this.usr_id = usr_id;
    }
}
