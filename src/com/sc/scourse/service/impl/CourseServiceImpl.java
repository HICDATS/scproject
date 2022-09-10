package com.sc.scourse.service.impl;

import com.sc.scourse.dao.CourseDao;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.service.CourseService;
import com.sc.scourse.service.S_C_ScoreService;

import java.util.ArrayList;
import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao;
    private S_C_ScoreService s_C_ScoreService;

    @Override
    public List<Course> getCourseByTid(Integer tid) {
        return courseDao.getCourseByTid(tid);
    }

    @Override
    public Course getCourseByCid(Integer cid) {
        return courseDao.getCourseByCid(cid);
    }

    @Override
    public List<Course> getAllCourse() {
        return courseDao.getAllCourse();
    }

    @Override
    public Integer getCidByCname(String cname) {
        return courseDao.getCidByCname(cname);
    }

    @Override
    public List<Course> getNeedAuditCourseByTid(Integer tid) {
        return courseDao.getNeedAuditCourseByTid(tid);
    }

    @Override
    public void deleteNeedAuditCourseByCname(String cname) {
        courseDao.deleteNeedAuditCourseByCname(cname);
    }

    @Override
    public void addNeedAuditCourse(String cname, String time, String address, Integer cap, Integer tid) {
        courseDao.addNeedAuditCourse(cname,time,address,cap,tid);
    }

    @Override
    public List<Course> getCourseByAddressAndTime(String address, String time) {
        return courseDao.getCourseByAddressAndTime(address,time);
    }

    @Override
    public void deleteCourseByCname(String cname) {
        Integer cid = courseDao.getCidByCname(cname);
        s_C_ScoreService.deleteS_C_ScoreByCname(cid);
        courseDao.deleteCourseByCname(cname);
    }

    @Override
    public List<Integer> getCidsByTidAndMoHuCname(String moHuCname) {
        List<Course> courses = courseDao.getCoursesByTidAndMoHuCname(moHuCname);
        ArrayList<Integer> cids = new ArrayList<>();
        for (Course course : courses) {
            cids.add(course.getCid());
        }
        return cids;
    }

    @Override
    public List<Course> getCoursesBySelectCidOrMoHuCname(String selectName, String value) {
        List<Course> courses = new ArrayList<>();
        if(selectName.equals("课程号")){
            if(!(value.matches("^[0-9]+$"))){
                return courses;
            }
            int cid = Integer.parseInt(value);
            Course courseByCid = courseDao.getCourseByCid(cid);
            if(courseByCid==null){
                return courses;
            }else{
                courses.add(courseByCid);
                return courses;
            }

        }else{
            return courseDao.getCoursesByTidAndMoHuCname(value);
        }
    }

    @Override
    public List<Course> getAllNeedAuditCourse() {
        return courseDao.getAllNeedAuditCourse();
    }

    @Override
    public void agreeNeedAuditCourse(String cname, String time, String address, Integer cap, Integer tid) {
        courseDao.deleteNeedAuditCourseByCname(cname);
        courseDao.addCourse(cname,time,address,cap,tid);
    }

    @Override
    public void addCourse(String cname, String time, String address, Integer cap, Integer tid) {
        courseDao.addCourse(cname, time, address, cap, tid);
    }
}
