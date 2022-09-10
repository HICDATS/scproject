package com.sc.scourse.pojo;

//专业必修课
public class RCourse {
    private Integer id; //id
    private Integer cid;//课程号
    private String major;//专业

    public RCourse() {}

    public RCourse(Integer id, Integer cid, String major) {
        this.id = id;
        this.cid = cid;
        this.major = major;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
