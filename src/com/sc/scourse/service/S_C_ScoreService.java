package com.sc.scourse.service;

import com.sc.scourse.pojo.AllScore;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.S_C_Score;

import java.util.List;
import java.util.Map;

public interface S_C_ScoreService {
    public List<S_C_Score> getCScoreBySid(Integer sid);

    public Map<Course, AllScore> getMapCScoreBySid(Integer sid);

    public void addCScore(Integer sid, Integer cid);

    //通过课程号查询该课程已选人数
    public Integer getCountOfCourse(Integer cid);

    //通过课程号及学号删除选课记录
    public void deleteCScoreBySidAndCid(Integer sid,Integer cid);

    //通过课程号查询已选的所有学生学号
    public List<S_C_Score> getSelectedS_C_Score(Integer cid);

    //通过课程号以及学号获取选课记录
    public S_C_Score getS_C_ScoreBySidAndCid(Integer sid,Integer cid);

    //通过学号，课程号及需修改的期末成绩值来修改期末成绩
    public void changeScore(Integer sid,Integer cid, Double newScore);

    //通过学号，课程号及需修改的期末成绩值来修改平时成绩
    public void changeUScore(Integer sid,Integer cid, Double newUScore);

    //通过课程名删除对应所有记录
    public void deleteS_C_ScoreByCname(Integer cid);

    //根据学号删除对应所有记录
    public void deleteS_C_ScoreBySid(Integer sid);
}
