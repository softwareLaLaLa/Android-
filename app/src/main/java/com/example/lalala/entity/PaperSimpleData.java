package com.example.lalala.entity;

import java.util.List;

public class PaperSimpleData {
    private PaperEntity paperEntity;
    private List<String> tags;

    public PaperEntity getPaperEntity() {
        return paperEntity;
    }

    public void setPaperEntity(PaperEntity paperEntity) {
        this.paperEntity = paperEntity;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
