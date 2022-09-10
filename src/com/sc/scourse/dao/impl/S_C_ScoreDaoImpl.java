package com.sc.scourse.dao.impl;

import com.sc.myssm.basedao.BaseDAO;
import com.sc.scourse.dao.CourseDao;
import com.sc.scourse.dao.S_C_ScoreDao;
import com.sc.scourse.pojo.AllScore;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.S_C_Score;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class S_C_ScoreDaoImpl extends BaseDAO<S_C_Score> implements S_C_ScoreDao {
    private CourseDao courseDao;

    //通过学号查询学号与课程号及成绩的记录
    @Override
    public List<S_C_Score> getCScoreBySid(Integer sid) {
        String sql = "select * from t_scs_detail where sid = ?";
        return executeQuery(sql,sid);
    }

    //通过学号获取该学生的课程信息及对应成绩的MAP集合
    @Override
    public Map<Course, AllScore> getMapCScoreBySid(Integer sid) {
        Map<Course,AllScore> mapSCList = new HashMap<>();
        List<S_C_Score> listSC = getCScoreBySid(sid);
        for (S_C_Score s_c_score : listSC) {
            Course courseByCid = courseDao.getCourseByCid(s_c_score.getCid());
            mapSCList.put(courseByCid,new AllScore(s_c_score.getScore(), s_c_score.getUscore()));
        }
        return mapSCList;
    }

    //添加一条记录
    @Override
    public void addCScore(Integer sid, Integer cid) {
        String sql = "insert into t_scs_detail(sid,cid) values( ? , ? )";
        executeUpdate(sql,sid,cid);
    }

    //通过课程号获取该课程已选的人数
    @Override
    public Integer getCountOfCourse(Integer cid) {
        String sql = "select count(*) from t_scs_detail where cid = ?";
        Object[] objects = executeComplexQuery(sql, cid);
        return ((Long) objects[0]).intValue();
    }

    //通过学号及课程号删除对应的选课信息
    @Override
    public void deleteCScoreBySidAndCid(Integer sid, Integer cid) {
        String sql = "delete from t_scs_detail where sid = ? and cid = ?";
        executeUpdate(sql,sid,cid);
    }

    //通过课程号获取选择该课程的所有学生学号
    @Override
    public List<S_C_Score> getSelectedS_C_Score(Integer cid) {
        String sql = "select sid from t_scs_detail where cid = ?";
        List<S_C_Score> list = executeQuery(sql, cid);
        return list;
    }

    @Override
    public S_C_Score getS_C_ScoreBySidAndCid(Integer sid, Integer cid) {
        String sql = "select * from t_scs_detail where sid = ? and cid = ?";
        S_C_Score s_c_score = load(sql, sid, cid);
        return s_c_score;
    }

    @Override
    public void changeScore(Integer sid,Integer cid, Double newScore) {
        String sql = "update t_scs_detail set score = ? where sid = ? and cid = ?";
        executeUpdate(sql,newScore,sid,cid);
    }

    @Override
    public void changeUScore(Integer sid, Integer cid, Double newUScore) {
        String sql = "update t_scs_detail set uscore = ? where sid = ? and cid = ?";
        executeUpdate(sql,newUScore,sid,cid);
    }

    @Override
    public void deleteS_C_ScoreByCname(Integer cid) {
        String sql = "delete from t_scs_detail where cid = ?";
        executeUpdate(sql,cid);
    }

    @Override
    public void deleteS_C_ScoreBySid(Integer sid) {
        String sql = "delete from t_scs_detail where sid = ?";
        executeUpdate(sql,sid);
    }

}
