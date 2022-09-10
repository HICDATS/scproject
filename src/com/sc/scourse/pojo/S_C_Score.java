package com.sc.scourse.pojo;

//课程-学生-成绩类
public class S_C_Score {
    private Integer id;
    private Integer sid;//
    private Integer cid;
    private Double uscore;
    private Double score;

    public S_C_Score() {}

    public S_C_Score(Integer id, Integer sid, Integer cid, Double uscore, Double score) {
        this.id = id;
        this.sid = sid;
        this.cid = cid;
        this.uscore = uscore;
        this.score = score;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getUscore() {
        return uscore;
    }

    public void setUscore(Double uscore) {
        this.uscore = uscore;
    }
}
