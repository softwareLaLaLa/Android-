package com.example.lalala.model;

import java.util.ArrayList;
import java.util.List;

public class PaperBrowseItem {
    private String title;
    private String author;
    private int cite;
    private int comment;
    private String DOI;
    private List<String> subjects = new ArrayList<>();

    public PaperBrowseItem(String title, String author, int cite, int comment, String DOI ,List<String> subjects) {
        this.title = title;
        this.author = author;
        this.cite = cite;
        this.comment = comment;
        this.DOI = DOI;
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getCite() {
        return cite;
    }

    public int getComment() {
        return comment;
    }

    public List<String> getSubjects() {
        return subjects;
    }
}
