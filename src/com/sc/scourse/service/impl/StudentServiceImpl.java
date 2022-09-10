package com.sc.scourse.service.impl;

import com.sc.scourse.dao.StudentDao;
import com.sc.scourse.pojo.*;
import com.sc.scourse.service.CourseService;
import com.sc.scourse.service.S_C_ScoreService;
import com.sc.scourse.service.StudentService;
import com.sc.scourse.service.UserService;
import com.sc.scourse.utils.TimeTableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentServiceImpl implements StudentService {

    private StudentDao studentDao;
    private S_C_ScoreService s_C_ScoreService;
    private CourseService courseService;
    private UserService userService;

    @Override
    public Student getStudentBySid(Integer sid) {
        Student student = studentDao.getStudentBySid(sid);
        Map<Course, AllScore> mapCScoreBySid = s_C_ScoreService.getMapCScoreBySid(sid);
        student.setCourses(mapCScoreBySid);
        Map<Course, AllScore> coursesMap = student.getCourses();
        Set<Course> courses = coursesMap.keySet();
        List<TimeTable> timeTableListByCourses = TimeTableUtil.getTimeTableListByCourses(courses);
        student.setTimeTables(timeTableListByCourses);
        return student;
    }

    @Override
    public List<Student> getStudentListByCname(String cname) {
        Integer cidByCname = courseService.getCidByCname(cname);
        List<S_C_Score> selectedS_c_score = s_C_ScoreService.getSelectedS_C_Score(cidByCname);
        List<Student> students = studentDao.getStudentListByS_C_ScoreList(selectedS_c_score);
        return students;
    }

    @Override
    public List<Integer> getSidByMoHuName(String moHuName) {
        List<Student> students = studentDao.getSidByMoHuName(moHuName);
        ArrayList<Integer> sids = new ArrayList<>();
        for (Student student : students) {
            sids.add(student.getSid());
        }

        return sids;
    }

    @Override
    public List<Student> getStudentListBySelectNameAndValue(String selectName, String value) {
        List<Student> students = new ArrayList<>();
        if (selectName.equals("全部")) {
            students = studentDao.getStudentListByMoHuName("");
        } else if (selectName.equals("学号")) {
            if (!(value.matches("^[0-9]+$"))) {
                return students;
            }
            students.add(studentDao.getStudentDetailBySid(Integer.parseInt(value)));
        } else if (selectName.equals("姓名")) {
            students = studentDao.getStudentListByMoHuName(value);
        } else if (selectName.equals("年级")) {
            students = studentDao.getStudentListByMoHuMajor(value);
        }
        return students;
    }

    @Override
    public void deleteStudent(Integer sid) {
        studentDao.deleteStudentBySid(sid);
        userService.deleteUserById(sid);
        s_C_ScoreService.deleteS_C_ScoreBySid(sid);
    }

    @Override
    public void updateStudent(Integer sid, String name, String sex, String major, String grade, String sclass, String acount, String pwd) {
        studentDao.updateStudentBySid(sid,name,sex,major,grade,sclass);
        userService.updateUserById(sid,acount,pwd);
    }

    @Override
    public boolean addStudent(Integer sid, String name, String sex, String major, String grade, String sclass, String pwd) {
        boolean ok = userService.addUser("s" + sid, pwd, "1",sid);
        //若用户表中有相同id则ok为false，或者student表中有相同sid不能继续添加
        if(!ok || studentDao.getStudentBySid(sid)!=null){
            return false;
        }
        studentDao.addStudent(sid,name,sex,major,grade,sclass);
        return true;
    }
}
