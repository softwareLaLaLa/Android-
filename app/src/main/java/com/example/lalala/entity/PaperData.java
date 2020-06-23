package com.example.lalala.entity;

import java.util.ArrayList;
import java.util.List;

public class PaperData {

    private int groupId;
    private String abst;
    private String resUrl;
    private List<TagSimpleData> existTag;
    private List<TagSimpleData> recomTag;

    public PaperData(){
        groupId=1;
        abst="这是摘要";
        resUrl="这是下载链接";
        List<TagSimpleData> existTags = new ArrayList<>();
        existTags.add(new TagSimpleData(1,"tag1"));
        existTags.add(new TagSimpleData(2,"tag2"));
        existTags.add(new TagSimpleData(3,"tag3"));
        existTags.add(new TagSimpleData(4,"tag4"));
        List<TagSimpleData> recTags = new ArrayList<>();
        recTags.add(new TagSimpleData(5,"tag5"));
        recTags.add(new TagSimpleData(6,"tag6"));
        recTags.add(new TagSimpleData(3,"tag3"));
        recTags.add(new TagSimpleData(4,"tag4"));
        existTag=existTags;
        recomTag=recTags;
    }
    public PaperData(int id, String abs, String url, List<TagSimpleData> exist, List<TagSimpleData> recom) {
        groupId = id;
        abst = abs;
        resUrl = url;
        existTag = exist;
        recomTag = recom;
    }

    public int getGroupId() {
        return groupId;
    }

    public String getAbst() {
        return abst;
    }

    public String getResUrl() {
        return resUrl;
    }

    public List<TagSimpleData> getExistTag() {
        return existTag;
    }

    public List<TagSimpleData> getRecomTag() {
        return recomTag;
    }


}
