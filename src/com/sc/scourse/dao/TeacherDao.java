package com.sc.scourse.dao;

import com.sc.scourse.pojo.Teacher;

import java.util.List;

public interface TeacherDao {
    public Teacher getTeacher(Integer tid);
    public String getTeacherName(Integer tid);

    //根据学工号获取单个教师所有信息（包括账号，密码等）
    public Teacher getTeacherDetailByTid(Integer tid);

    //根据学生姓名模糊查询学生列表
    public List<Teacher> getTeacherListByMoHuName(String moHuName);

    //根据学院名称模糊查询教师列表
    public List<Teacher> getTeacherListByMoHuCollege(String moHuCollege);

    //根据学工号删除教师信息
    public void deleteTeacherByTid(Integer sid);

    //根据学工号修改教师信息
    public void updateTeacherByTid(Integer tid, String name, String sex, String college, String jobTitle);

    //添加教师信息
    public void addTeacher(Integer tid,String name, String sex, String college, String jobTitle);


}
