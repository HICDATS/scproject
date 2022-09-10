package com.sc.scourse.dao.impl;

import com.sc.myssm.basedao.BaseDAO;
import com.sc.scourse.dao.StudentDao;
import com.sc.scourse.pojo.*;
import com.sc.scourse.service.S_C_ScoreService;
import com.sc.scourse.utils.TimeTableUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StudentDaoImpl extends BaseDAO<Student> implements StudentDao {
    private S_C_ScoreService s_C_ScoreService;

    @Override
    public Student getStudentBySid(Integer sid) {
        String sql = "select * from t_st_detail where sid = ? ";
        Student student = load(sql, sid);

        return student;
    }

    @Override
    public List<Student> getStudentListByS_C_ScoreList(List<S_C_Score> list){
        ArrayList<Student> students = new ArrayList<>();
        for (S_C_Score s_c_score : list) {
            Student studentBySid = getStudentBySid(s_c_score.getSid());
            students.add(studentBySid);
        }
        return students;
    }

    @Override
    public List<Student> getSidByMoHuName(String moHuName) {
        String sql = "select sid from t_st_detail where name like \"%\"?\"%\"";
        List<Student> students = executeQuery(sql, moHuName);
        return students;
    }

    @Override
    public Student getStudentDetailBySid(Integer sid) {
        String sql = "select sid,name,sex,major,grade,sclass,acount,pwd from t_st_detail LEFT JOIN t_user_login on t_st_detail.sid = t_user_login.id where sid = ?";
        return load(sql,sid);
    }

    @Override
    public List<Student> getStudentListByMoHuName(String moHuName) {
        String sql = "select sid,name,sex,major,grade,sclass,acount,pwd from t_st_detail LEFT JOIN t_user_login on t_st_detail.sid = t_user_login.id where name like \"%\"?\"%\"";
        return executeQuery(sql,moHuName);
    }

    @Override
    public List<Student> getStudentListByMoHuMajor(String moHuMajor) {
        String sql = "select sid,name,sex,major,grade,sclass,acount,pwd from t_st_detail LEFT JOIN t_user_login on t_st_detail.sid = t_user_login.id where grade like \"%\"?\"%\"";
        return executeQuery(sql,moHuMajor);
    }

    @Override
    public void deleteStudentBySid(Integer sid) {
        String sql = "delete from t_st_detail where sid = ?";
        executeUpdate(sql,sid);
    }

    @Override
    public void updateStudentBySid( Integer sid ,String name, String sex, String major, String grade, String sclass) {
        String sql = "update t_st_detail set name = ? , sex = ? , major = ? , grade = ? , sclass = ? where sid = ?";
        executeUpdate(sql,name,sex,major,grade,sclass,sid);
    }

    @Override
    public void addStudent(Integer sid, String name, String sex, String major, String grade, String sclass) {
        String sql = "insert into t_st_detail values(?,?,?,?,?,?)";
        executeUpdate(sql,sid,name,sex,major,grade,sclass);
    }
}
