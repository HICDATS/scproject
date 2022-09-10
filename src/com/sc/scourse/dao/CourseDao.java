package com.sc.scourse.dao;

import com.sc.scourse.pojo.Course;

import java.util.List;
import java.util.Map;

public interface CourseDao {
    public List<Course> getCourseByTid(Integer tid);

    //根据课程号查询课程
    public Course getCourseByCid(Integer cid);

    public List<Course> getAllCourse();

    //根据课程名获取课程cid
    public Integer getCidByCname(String cname);

    //根据老师学工号获取待审核课程
    public List<Course> getNeedAuditCourseByTid(Integer tid);

    //根据课程名删除待审核课程
    public void deleteNeedAuditCourseByCname(String cname);

    //根据课程信息添加待审核课程
    public void addNeedAuditCourse(String cname,String time,String address,Integer cap,Integer tid);

    //通过上课地点和时间获取现有课程
    public List<Course> getCourseByAddressAndTime(String address,String time);

    //通过课程名删除课程记录
    public void deleteCourseByCname(String cname);

    //通过模糊课程名查询课程
    public List<Course> getCoursesByTidAndMoHuCname(String moHuCname);


    //获取所有待审核的课程
    public List<Course> getAllNeedAuditCourse();

    //向课程列表中添加课程
    public void addCourse(String cname,String time,String address,Integer cap,Integer tid);
}
