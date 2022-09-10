package com.sc.scourse.service;

import com.sc.scourse.pojo.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher getTeacher(Integer tid);

    public String getTeacherName(Integer tid);

    //选择查询方式对教师详细信息进行查询
    public List<Teacher> getTeacherListBySelectNameAndValue(String selectName, String value);

    //根据学工号删除教师信息以及用户登录表中的信息
    public void deleteTeacher(Integer tid);

    //根据学工号修改教师所有信息，包括账号密码等
    public void updateTeacher(Integer tid, String name, String sex, String college, String jobTitle , String acount, String pwd);


    //添加一个教师信息，并添加到登录用户中
    public boolean addTeacher(Integer tid, String name, String sex, String college, String jobTitle, String pwd);
}
