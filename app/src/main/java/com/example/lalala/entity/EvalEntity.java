package com.example.lalala.entity;

import java.util.Date;
public class EvalEntity {
    private int id;

    private int usrId;

    private int paperId;

    private float eval;

    //@Column(name = "tags")
    //private String tagIDList;

    private Date date;

    public Date getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getUsrid() {
        return usrId;
    }

    public int getPaperid() {
        return paperId;
    }

    public float getEval() {
        return eval;
    }

//    public List<Integer> getTagIDList() {
//        Gson gson = new Gson();
//        Type dataListType = new TypeToken<ArrayList<Integer>>(){}.getType();
//        return  gson.fromJson(tagIDList, dataListType);
//    }

    public EvalEntity(int usrid, int paperid, float eval, Date date) {
        this.usrId = usrid;
        this.paperId = paperid;
        this.eval = eval;
        //this.tagIDList = tagIDList;
        this.date = date;
    }

    public EvalEntity(){}

    public void setId(int id) {
        this.id = id;
    }

    public void setUsrId(int usrId) {
        this.usrId = usrId;
    }

    public void setPaperId(int paperId) {
        this.paperId = paperId;
    }

    public void setEval(float eval) {
        this.eval = eval;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date){this.date = new Date(date);}
}
