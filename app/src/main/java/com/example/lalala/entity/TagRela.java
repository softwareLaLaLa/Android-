package com.example.lalala.entity;

import java.io.Serializable;

public class TagRela implements Serializable {
    public float getCorrelation() {
        return correlation;
    }

    public int getTag_num() {
        return tag_num;
    }

    int tag_num;
    float correlation;

    public TagRela(int tag_num, float correlation) {
        this.tag_num = tag_num;
        this.correlation = correlation;
    }

    public void setCorrelation(float correlation) {
        this.correlation = correlation;
    }

    public void addNum(){
        ++tag_num;
    }

    public void setTag_num(int num){
        this.tag_num = num;
    }

    public String toString(){
        return "tagNum:" + tag_num + " correlation:"+correlation;
    }
}
