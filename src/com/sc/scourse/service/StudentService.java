package com.sc.scourse.service;

import com.sc.scourse.pojo.Student;

import java.util.List;

public interface StudentService {
    public Student getStudentBySid(Integer sid);

    //通过学号数组获取一个学生列表
    public List<Student> getStudentListByCname(String cname);

    //根据姓名模糊查询一位学生的sid
    public List<Integer> getSidByMoHuName(String moHuName);

    //选择查询方式对学生详细信息进行查询
    public List<Student> getStudentListBySelectNameAndValue(String selectName, String value);

    //根据学号删除学生信息以及用户登录表中的信息
    public void deleteStudent(Integer sid);

    //根据学号修改学生所有信息，包括账号密码等
    public void updateStudent(Integer sid,String name, String sex, String major, String grade, String sclass,String acount,String pwd);

    //添加一个学生信息，并添加到登录用户中
    public boolean addStudent(Integer sid,String name, String sex, String major, String grade, String sclass,String pwd);
}
