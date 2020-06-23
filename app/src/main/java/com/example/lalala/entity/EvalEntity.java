package com.example.lalala.entity;

import java.util.Date;

public class EvalEntity {

    private int usrId;

    private int paperId;

    private float eval;

    private Date date;

    public EvalEntity(int usrid, int paperid, float eval, Date date) {
        this.usrId = usrid;
        this.paperId = paperid;
        this.eval = eval;
        this.date = date;
    }

    public EvalEntity(){}

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

}
