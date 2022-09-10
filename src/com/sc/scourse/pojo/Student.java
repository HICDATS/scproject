package com.sc.scourse.pojo;

import java.util.List;
import java.util.Map;

//学生类
public class Student {
    private String acount;//账号
    private String pwd;//密码
    private Integer sid;//学号
    private String name;//姓名
    private String sex;//性别
    private String major;//专业
    private String grade;//年级
    private String sclass;//班级

    private Map<Course,AllScore> courses;//课程列表
    private List<TimeTable> timeTables;//课程表

    public Map<Course,AllScore> getCourses() {
        return courses;
    }

    public void setCourses(Map<Course,AllScore> courses) {
        this.courses = courses;
    }

    public Student() {}

    public Student(Integer sid, String name, String sex, String major) {
        this.sid = sid;
        this.name = name;
        this.sex = sex;
        this.major = major;
    }

    public Student(String acount, String password, Integer sid, String name, String sex, String major, String grade, String sclass) {
        this.acount = acount;
        this.pwd = password;
        this.sid = sid;
        this.name = name;
        this.sex = sex;
        this.major = major;
        this.grade = grade;
        this.sclass = sclass;
    }

    public String getAcount() {
        return acount;
    }

    public void setAcount(String acount) {
        this.acount = acount;
    }

    public String getPassword() {
        return pwd;
    }

    public void setPassword(String password) {
        this.pwd = password;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
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

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    public List<TimeTable> getTimeTables() {
        return timeTables;
    }

    public void setTimeTables(List<TimeTable> timeTables) {
        this.timeTables = timeTables;
    }
}
