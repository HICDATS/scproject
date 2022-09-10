package com.sc.scourse.service;

import com.sc.scourse.pojo.Course;

import java.util.List;

public interface CourseService {
    public List<Course> getCourseByTid(Integer tid);

    public Course getCourseByCid(Integer cid);

    public List<Course> getAllCourse();

    //根据课程名获取课程cid
    public Integer getCidByCname(String cname);

    //获取所有的待审核课程
    public List<Course> getNeedAuditCourseByTid(Integer tid);

    //根据课程名删除待审核课程
    public void deleteNeedAuditCourseByCname(String cname);

    //根据课程信息添加待审核课程
    public void addNeedAuditCourse(String cname,String time,String address,Integer cap,Integer tid);

    //通过上课地点和时间获取现有课程
    public List<Course> getCourseByAddressAndTime(String address,String time);

    //通过课程名删除课程记录
    public void deleteCourseByCname(String cname);

    //通过学工号以及模糊课程名查询课程cids
    public List<Integer> getCidsByTidAndMoHuCname(String moHuCname);

    //根据查询内容和查询值选择查询课程
    public List<Course> getCoursesBySelectCidOrMoHuCname(String selectName,String value);

    //获取所有待审核的课程
    public List<Course> getAllNeedAuditCourse();

    //通过待审核课程，将待审核课程删除，移至课程列表中
    public void agreeNeedAuditCourse(String cname,String time,String address,Integer cap,Integer tid);

    public void addCourse(String cname,String time,String address,Integer cap,Integer tid);
}
