package com.sc.scourse.dao.impl;

import com.sc.myssm.basedao.BaseDAO;
import com.sc.scourse.dao.CourseDao;
import com.sc.scourse.dao.TeacherDao;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.Teacher;

import java.util.List;

public class TeacherDaoImpl extends BaseDAO<Teacher> implements TeacherDao {

    @Override
    public Teacher getTeacher(Integer tid) {
        String sql = "select * from t_tc_detail where tid = ?";
        Teacher teacher = load(sql,tid);
        return teacher;
    }

    public String getTeacherName(Integer tid){
        String sql = "select * from t_tc_detail where tid = ?";
        Teacher teacher = load(sql,tid);
        if(teacher == null){
            return null;
        }
        return teacher.getName();
    }

    @Override
    public Teacher getTeacherDetailByTid(Integer tid) {
        String sql = "select tid,name,sex,college,jobTitle,acount,pwd from t_tc_detail LEFT JOIN t_user_login on t_tc_detail.tid = t_user_login.id where tid = ?";
        Teacher teacher = load(sql,tid);
        return teacher;
    }

    @Override
    public List<Teacher> getTeacherListByMoHuName(String moHuName) {
        String sql = "select tid,name,sex,college,jobTitle,acount,pwd from t_tc_detail LEFT JOIN t_user_login on t_tc_detail.tid = t_user_login.id where name like \"%\"?\"%\"";
        return executeQuery(sql,moHuName);
    }

    @Override
    public List<Teacher> getTeacherListByMoHuCollege(String moHuCollege) {
        String sql = "select tid,name,sex,college,jobTitle,acount,pwd from t_tc_detail LEFT JOIN t_user_login on t_tc_detail.tid = t_user_login.id where college like \"%\"?\"%\"";
        return executeQuery(sql,moHuCollege);
    }

    @Override
    public void deleteTeacherByTid(Integer tid) {
        String sql = "delete from t_tc_detail where tid = ?";
        executeUpdate(sql,tid);
    }

    @Override
    public void updateTeacherByTid(Integer tid, String name, String sex, String college, String jobTitle) {
        String sql = "update t_tc_detail set name = ? , sex = ? , college = ? , jobTitle = ?  where tid = ?";
        executeUpdate(sql,name,sex,college,jobTitle,tid);
    }

    @Override
    public void addTeacher(Integer tid, String name, String sex, String college, String jobTitle) {
        String sql = "insert into t_tc_detail values(?,?,?,?,?)";
        executeUpdate(sql,tid,name,sex,college,jobTitle);
    }
}
