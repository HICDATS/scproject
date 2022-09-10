package com.sc.scourse.pojo;

public class ScoreShow {
    private Integer sid;
    private String sname;
    private Integer cid;
    private String cname;
    private Double uScore;
    private Double score;
    private Double tScore;

    public ScoreShow(Integer cid, String cname, Double uScore, Double score) {
        this.cid = cid;
        this.cname = cname;
        this.uScore = uScore;
        this.score = score;
    }

    public ScoreShow(Integer sid, String sname, String cname, Double uScore, Double score) {
        this.sid = sid;
        this.sname = sname;
        this.cname = cname;
        this.uScore = uScore;
        this.score = score;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Double getuScore() {
        return uScore;
    }

    public void setuScore(Double uScore) {
        this.uScore = uScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double gettScore() {
        if(this.uScore==null ||this.score==null){
            this.tScore = null;
        }else{
            this.tScore = Double.parseDouble(String.format("%.2f",this.uScore*0.4+this.score*0.6));
        }
        return this.tScore;
    }

}
