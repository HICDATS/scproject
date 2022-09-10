package com.sc.scourse.pojo;

//课程详情类
public class Course {
    private Integer cid ;//课程号
    private String cname ;//课程名
    private String time;//上课时间
    private String address;//上课地点
    private Integer cap;//容量
    private Integer snum;//已选人数
    private Integer tid;//所属教师学工号
    private String teachername;//所属教师姓名
    private String[] timeArr;

    public Course() {}

    public Course(Integer cid, String cname, String time, String address, Integer cap, Integer snum, Integer tid) {
        this.cid = cid;
        this.cname = cname;
        this.time = time;
        this.address = address;
        this.cap = cap;
        this.snum = snum;
        this.tid = tid;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCap() {
        return cap;
    }

    public void setCap(Integer cap) {
        this.cap = cap;
    }

    public Integer getSnum() {
        return snum;
    }

    public void setSnum(Integer snum) {
        this.snum = snum;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public String[] getTimeArr() {
        return timeArr;
    }

    public void setTimeArr(String[] timeArr) {
        this.timeArr = timeArr;
    }
}
