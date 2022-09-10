package com.sc.scourse.dao;

import com.sc.scourse.pojo.S_C_Score;
import com.sc.scourse.pojo.Student;

import java.util.List;

public interface StudentDao {
    //通过学生学号获取单个学生
    public Student getStudentBySid(Integer sid);

    //通过学号数组获取一个学生列表
    public List<Student> getStudentListByS_C_ScoreList(List<S_C_Score> list);

    //根据姓名模糊查询一位学生的sid
    public List<Student> getSidByMoHuName(String moHuName);

    //根据学生学号获取单个学生所有信息（包括账号，密码等）
    public Student getStudentDetailBySid(Integer sid);

    //根据学生姓名模糊查询学生列表
    public List<Student> getStudentListByMoHuName(String moHuName);

    //根据学生专业模糊查询学生列表
    public List<Student> getStudentListByMoHuMajor(String moHuMajor);

    //根据学号删除学生信息
    public void deleteStudentBySid(Integer sid);

    //根据学号修改学生信息
    public void updateStudentBySid(Integer sid, String name, String sex, String major, String grade, String sclass);

    //添加学生信息
    public void addStudent(Integer sid,String name,String sex,String major,String grade,String sclass);

}
