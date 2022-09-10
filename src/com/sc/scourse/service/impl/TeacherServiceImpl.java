package com.sc.scourse.service.impl;

import com.sc.scourse.dao.TeacherDao;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.Student;
import com.sc.scourse.pojo.Teacher;
import com.sc.scourse.pojo.TimeTable;
import com.sc.scourse.service.CourseService;
import com.sc.scourse.service.S_C_ScoreService;
import com.sc.scourse.service.TeacherService;
import com.sc.scourse.service.UserService;
import com.sc.scourse.utils.TimeTableUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TeacherServiceImpl implements TeacherService {

    private TeacherDao teacherDao;
    private CourseService courseService;
    private UserService userService;
    private S_C_ScoreService s_C_ScoreService;

    @Override
    public Teacher getTeacher(Integer tid) {
        Teacher teacher = teacherDao.getTeacher(tid);
        List<Course> courseList = courseService.getCourseByTid(teacher.getTid());
        teacher.setCourses(courseList);
        HashSet<Course> coursesSet = new HashSet<>();
        coursesSet.addAll(courseList);
        List<TimeTable> timeTables = TimeTableUtil.getTimeTableListByCourses(coursesSet);
        teacher.setTimeTables(timeTables);
        return teacher;
    }

    @Override
    public String getTeacherName(Integer tid) {
        return teacherDao.getTeacherName(tid);
    }

    @Override
    public List<Teacher> getTeacherListBySelectNameAndValue(String selectName, String value) {
        List<Teacher> teachers = new ArrayList<>();
        if (selectName.equals("全部")) {
            teachers = teacherDao.getTeacherListByMoHuName("");
        } else if (selectName.equals("学工号")) {
            if (!(value.matches("^[0-9]+$"))) {
                return teachers;
            }
            teachers.add(teacherDao.getTeacherDetailByTid(Integer.parseInt(value)));
        } else if (selectName.equals("姓名")) {
            teachers = teacherDao.getTeacherListByMoHuName(value);
        } else if (selectName.equals("学院")) {
            teachers = teacherDao.getTeacherListByMoHuCollege(value);
        }
        return teachers;
    }

    @Override
    public void deleteTeacher(Integer tid) {
        teacherDao.deleteTeacherByTid(tid);
        userService.deleteUserById(tid);
        s_C_ScoreService.deleteS_C_ScoreBySid(tid);
    }

    @Override
    public void updateTeacher(Integer tid, String name, String sex, String college, String jobTitle, String acount, String pwd) {
        teacherDao.updateTeacherByTid(tid,name,sex,college,jobTitle);
        userService.updateUserById(tid,acount,pwd);
    }

    @Override
    public boolean addTeacher(Integer tid, String name, String sex, String college, String jobTitle, String pwd) {
        boolean ok = userService.addUser("t" + tid, pwd, "2",tid);
        //若用户表中有相同id则ok为false，或者student表中有相同sid不能继续添加
        if(!ok || teacherDao.getTeacher(tid)!=null){
            return false;
        }
        teacherDao.addTeacher(tid,name,sex,college,jobTitle);
        return true;
    }
}
