package com.sc.scourse.pojo;

//成绩类，用于存储平时成绩和期末分数
public class AllScore {
    //期末分数
    private Double score;
    //平时成绩
    private Double uScore;

    public AllScore(Double score, Double uScore) {
        this.score = score;
        this.uScore = uScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getuScore() {
        return uScore;
    }

    public void setuScore(Double uScore) {
        this.uScore = uScore;
    }
}
