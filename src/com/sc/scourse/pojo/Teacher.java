package com.sc.scourse.pojo;

import java.util.ArrayList;
import java.util.List;

//老师类
public class Teacher {
    private String acount;//账号
    private String pwd;//密码
    private Integer tid ;//学工号
    private String name ;//名字
    private String sex;//性别
    private String college;//所属学院
    private String jobTitle;//职称
    private List<Course> courses;//课程列表
    private List<TimeTable> timeTables;//课程表

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public Teacher() {}

    public Teacher(Integer tid, String name, String sex, String college, String jobTitle) {
        this.tid = tid;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.jobTitle = jobTitle;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }
}
