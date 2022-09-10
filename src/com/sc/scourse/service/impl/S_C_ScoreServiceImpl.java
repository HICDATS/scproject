package com.sc.scourse.service.impl;

import com.sc.scourse.dao.S_C_ScoreDao;
import com.sc.scourse.pojo.AllScore;
import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.S_C_Score;
import com.sc.scourse.service.S_C_ScoreService;

import java.util.List;
import java.util.Map;

public class S_C_ScoreServiceImpl implements S_C_ScoreService {

    private S_C_ScoreDao s_C_ScoreDao;
    @Override
    public List<S_C_Score> getCScoreBySid(Integer sid) {
        return s_C_ScoreDao.getCScoreBySid(sid);
    }

    @Override
    public Map<Course, AllScore> getMapCScoreBySid(Integer sid) {
        return s_C_ScoreDao.getMapCScoreBySid(sid);
    }

    @Override
    public void addCScore(Integer sid, Integer cid) {
        s_C_ScoreDao.addCScore(sid,cid);
    }

    @Override
    public Integer getCountOfCourse(Integer cid) {
        return s_C_ScoreDao.getCountOfCourse(cid);
    }

    @Override
    public void deleteCScoreBySidAndCid(Integer sid, Integer cid) {
        s_C_ScoreDao.deleteCScoreBySidAndCid(sid,cid);
    }

    @Override
    public List<S_C_Score> getSelectedS_C_Score(Integer cid) {
        return s_C_ScoreDao.getSelectedS_C_Score(cid);
    }

    @Override
    public S_C_Score getS_C_ScoreBySidAndCid(Integer sid, Integer cid) {
        return s_C_ScoreDao.getS_C_ScoreBySidAndCid(sid,cid);
    }

    @Override
    public void changeScore(Integer sid, Integer cid, Double newScore) {
        s_C_ScoreDao.changeScore(sid,cid,newScore);
    }

    @Override
    public void changeUScore(Integer sid, Integer cid, Double newUScore) {
        s_C_ScoreDao.changeUScore(sid,cid,newUScore);
    }

    @Override
    public void deleteS_C_ScoreByCname(Integer cid) {
        s_C_ScoreDao.deleteS_C_ScoreByCname(cid);
    }

    @Override
    public void deleteS_C_ScoreBySid(Integer sid) {
        s_C_ScoreDao.deleteS_C_ScoreBySid(sid);
    }


}
