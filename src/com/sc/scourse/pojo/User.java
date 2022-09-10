package com.sc.scourse.pojo;

//用户类
public class User {
    private String acount;//账号
    private String pwd ;//密码
    private String role;//角色
    private Integer id;//该用户的id（学生为学号，教师为学工号）

    public User() {}

    public User(String acount, String pwd, String role, Integer id) {
        this.acount = acount;
        this.pwd = pwd;
        this.role = role;
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
