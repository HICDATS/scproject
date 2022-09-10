package com.sc.scourse.dao.impl;

import com.sc.myssm.basedao.BaseDAO;
import com.sc.scourse.dao.CourseDao;
import com.sc.scourse.dao.S_C_ScoreDao;
import com.sc.scourse.dao.TeacherDao;
import com.sc.scourse.dao.UserDao;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.Teacher;
import com.sc.scourse.pojo.User;

import java.util.List;
import java.util.Map;

public class CourseDaoImpl extends BaseDAO<Course> implements CourseDao {

    private TeacherDao teacherDao;
    private S_C_ScoreDao s_C_ScoreDao;

    @Override
    public List<Course> getCourseByTid(Integer tid) {
        String sql = "select * from t_course_detail where tid = ?";
        List<Course> courses = executeQuery(sql, tid);
        for (Course course : courses) {
            course.setTimeArr(course.getTime().split("，"));
        }
        return courses;
    }

    @Override
    public Course getCourseByCid(Integer cid) {
        String sql = "select * from t_course_detail where cid = ?";
        Course course = load(sql, cid);
        if(course==null){
            return course;
        }
        Integer countOfCourse = s_C_ScoreDao.getCountOfCourse(course.getCid());
        course.setSnum(countOfCourse);
        course.setTimeArr(course.getTime().split("，"));
        return course;
    }

    @Override
    public List<Course> getAllCourse() {
        String sql = "select * from t_course_detail";
        List<Course> courses = executeQuery(sql);
        for (Course course : courses) {
            String teacherName = teacherDao.getTeacherName(course.getTid());
            course.setTeachername(teacherName);
            Integer countOfCourse = s_C_ScoreDao.getCountOfCourse(course.getCid());
            course.setSnum(countOfCourse);
            course.setTimeArr(course.getTime().split("，"));
        }
        return courses;
    }

    @Override
    public Integer getCidByCname(String cname) {
        String sql = "select cid from t_course_detail where cname = ?";
        Object[] objects = executeComplexQuery(sql, cname);
        //当课程名不存在则返回-1
        if(objects==null){
            return -1;
        }
        //当课程名存在返回对应课程号
        Integer cid = (Integer) objects[0];
        return cid;
    }

    @Override
    public List<Course> getNeedAuditCourseByTid(Integer tid) {
        String sql = "select * from t_course_audit where tid = ?";
        return executeQuery(sql,tid);
    }

    @Override
    public void deleteNeedAuditCourseByCname(String cname) {
        String sql = "delete from t_course_audit where cname = ?";
        executeUpdate(sql,cname);
    }

    @Override
    public void addNeedAuditCourse(String cname, String time, String address, Integer cap, Integer tid) {
        String sql = "insert into t_course_audit values(null,?,?,?,?,?)";
        executeUpdate(sql,cname,time,address,cap,tid);
    }

    @Override
    public List<Course> getCourseByAddressAndTime(String address, String time) {
        String sql = "select * from t_course_detail where address = ? and time = ?";
        return executeQuery(sql,address,time);
    }

    @Override
    public void deleteCourseByCname(String cname) {
        String sql = "delete from t_course_detail where cname = ?";
        executeUpdate(sql,cname);
    }

    @Override
    public List<Course> getCoursesByTidAndMoHuCname(String moHuCname) {
        String sql = "select * from t_course_detail where cname like \"%\"?\"%\"";
        List<Course> courses = executeQuery(sql, moHuCname);
        for (Course course : courses) {
            String teacherName = teacherDao.getTeacherName(course.getTid());
            course.setTeachername(teacherName);
            Integer countOfCourse = s_C_ScoreDao.getCountOfCourse(course.getCid());
            course.setSnum(countOfCourse);
            course.setTimeArr(course.getTime().split("，"));
        }
        return courses;
    }

    @Override
    public List<Course> getAllNeedAuditCourse() {
        String sql = "select * from t_course_audit";
        return executeQuery(sql);
    }

    @Override
    public void addCourse(String cname,String time,String address,Integer cap,Integer tid) {
        String sql = "insert into t_course_detail values(null,?,?,?,?,?)";
        executeUpdate(sql,cname,time,address,cap,tid);
    }

//    @Override
//    public List<Course> getCoursesByTidAndMoHuCname(Integer tid, String moHuCname) {
//        String sql = "";
//    }
}
