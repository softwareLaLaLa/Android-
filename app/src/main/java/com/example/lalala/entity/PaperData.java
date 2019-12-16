package com.example.lalala.entity;

import java.util.ArrayList;
import java.util.List;

public class PaperData {
    String abst;
    String resUrl;
    List<TagSimpleData> existTag = new ArrayList<>();
    List<TagSimpleData> recomTag = new ArrayList<>();

    public PaperData(PaperEntity paperEntity, List<List<TagSimpleData>> tagData){
        this.abst = paperEntity.getAbst();
        this.resUrl = paperEntity.getResUrl();
        existTag = tagData.get(0);
        recomTag = tagData.get(1);
    }
}
