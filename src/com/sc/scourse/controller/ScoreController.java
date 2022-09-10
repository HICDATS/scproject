package com.sc.scourse.controller;

import com.google.gson.Gson;
import com.sc.scourse.pojo.*;
import com.sc.scourse.service.CourseService;
import com.sc.scourse.service.S_C_ScoreService;
import com.sc.scourse.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ScoreController {
    private StudentService studentService;
    private S_C_ScoreService s_C_ScoreService;
    private CourseService courseService;

    //当前学生用户获取自己的课程及对应成绩
    public String getScore(HttpSession session) {
        //为手动创建json对象格式做准备
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        //从session域中获取当前学生对象，从而在学生对象中获取已选的课程集合
        Student currStudent = (Student) session.getAttribute("currStudent");
        Map<Course, AllScore> coursesMap = currStudent.getCourses();
        Set<Course> courses = coursesMap.keySet();
        //若没有已选课程，则直接返回一个数据为空的json格式的字符串
        if (courses.size() == 0) {
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        //若已选课程不为空，则将集合中每个课程及在map中对应的课程成绩封装为一个成绩展示的类，并组装到字符串中，便于前端layui获取数据
        before += courses.size() + ",\"data\":[";
        for (Course course : courses) {
            ScoreShow scoreShow = new ScoreShow(course.getCid(), course.getCname(), coursesMap.get(course).getuScore(), coursesMap.get(course).getScore());
            scoreShow.gettScore();
            String s = gson.toJson(scoreShow);
            before += s + ",";
        }

        return before.substring(0, before.length() - 1) + "]}";
    }

    //当前老师用户查询所授课程的所有学生以及成绩
    public String getScoreFroTeacher(HttpSession session, String selectName, String value) {
        List<ScoreShow> oldScoreShows = (List<ScoreShow>) session.getAttribute("scoreShows");
        Gson gson = new Gson();
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Object updated = session.getAttribute("updated");
        Object changeUser = session.getAttribute("changeUser");
        //若scoreShows不为空则判断是否进行筛选查询
        if (oldScoreShows != null && (updated==null) && (changeUser==null)) {

            //当只是layui获取数据时，则selectName为null，此时将原有的oldScoreShows传输到页面
            //若oldScoreShows无数据
            if(oldScoreShows.size()==0){
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += oldScoreShows.size() + ",\"data\":[";
            for (ScoreShow oldScoreShow : oldScoreShows) {
                String s = gson.toJson(oldScoreShow);
                before += s + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        }
        session.setAttribute("changeUser",null);
        session.setAttribute("updated",null);
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        List<Course> courses = currTeacher.getCourses();
        List<ScoreShow> scoreShows = new ArrayList<>();
        //为手动创建json对象格式做准备
        if (courses.size() == 0) {
            session.setAttribute("scoreShows", scoreShows);
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        boolean hasStudent = false;
        for (Course course : courses) {
            String cname = course.getCname();
            Integer cid = course.getCid();
            List<Student> studentListByCname = studentService.getStudentListByCname(cname);
            for (Student student : studentListByCname) {
                hasStudent = true;
                Integer sid = student.getSid();
                S_C_Score s_C_Score = s_C_ScoreService.getS_C_ScoreBySidAndCid(sid, cid);
                ScoreShow scoreShow = new ScoreShow(sid, student.getName(), cname, s_C_Score.getUscore(), s_C_Score.getScore());
                scoreShow.gettScore();
                scoreShows.add(scoreShow);
            }
        }
        before += scoreShows.size() + ",\"data\":[";
        for (ScoreShow scoreShow : scoreShows) {
            String s = gson.toJson(scoreShow);
            before += s + ",";
        }
        if(!hasStudent){
            session.setAttribute("scoreShows", scoreShows);
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        session.setAttribute("scoreShows", scoreShows);
        return before.substring(0, before.length() - 1) + "]}";
    }

    //当前老师在查询成绩界面选择筛选查询内容
    public String selectDataScoreFroTeacher(HttpSession session, String selectName, String value) {
        if (selectName.equals("全部") || value.equals("")) {
            session.setAttribute("scoreShows", null);
            return "";
        } else {
            Gson gson = new Gson();
            //当点击查询到达该函数时，根据查询的内容进行session域中scoreShows的修改
            //当选择查询的属性为学号时
            if (selectName.equals("学号")) {
                Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
                List<Course> courses = currTeacher.getCourses();
                List<ScoreShow> scoreShows = new ArrayList<>();
                if(!(value.matches("^[0-9]+$"))){
                    session.setAttribute("scoreShows", scoreShows);
                    return "";
                }
                //为手动创建json对象格式做准备
                if (courses.size() == 0) {
                    session.setAttribute("scoreShows", null);
                    return "";
                }
                for (Course course : courses) {
                    String cname = course.getCname();
                    Integer cid = course.getCid();
                    List<Student> studentListByCname = studentService.getStudentListByCname(cname);
                    for (Student student : studentListByCname) {
                        //判断与查询的学号是否相等
                        if (student.getSid() == Integer.parseInt(value)) {
                            Integer sid = student.getSid();
                            S_C_Score s_C_Score = s_C_ScoreService.getS_C_ScoreBySidAndCid(sid, cid);
                            ScoreShow scoreShow = new ScoreShow(sid, student.getName(), cname, s_C_Score.getUscore(), s_C_Score.getScore());
                            scoreShow.gettScore();
                            scoreShows.add(scoreShow);
                        }
                    }
                }
                session.setAttribute("scoreShows", scoreShows);
                return "";
            } else if(selectName.equals("课程名")){
                Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
                List<Course> courses = currTeacher.getCourses();
                List<ScoreShow> scoreShows = new ArrayList<>();
                if (courses.size() == 0) {
                    session.setAttribute("scoreShows", null);
                    return "";
                }
                //获取模糊查询后的所有课程cid
                List<Integer> cids = courseService.getCidsByTidAndMoHuCname(value);
                for (Course course : courses) {
                    if(cids.contains(course.getCid())){
                        String cname = course.getCname();
                        Integer cid = course.getCid();
                        List<Student> studentListByCname = studentService.getStudentListByCname(cname);
                        for (Student student : studentListByCname) {
                            Integer sid = student.getSid();
                            S_C_Score s_C_Score = s_C_ScoreService.getS_C_ScoreBySidAndCid(sid, cid);
                            ScoreShow scoreShow = new ScoreShow(sid, student.getName(), cname, s_C_Score.getUscore(), s_C_Score.getScore());
                            scoreShow.gettScore();
                            scoreShows.add(scoreShow);
                        }
                    }
                }
                session.setAttribute("scoreShows", scoreShows);
                return "";
            }else{
                Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
                List<Course> courses = currTeacher.getCourses();
                List<ScoreShow> scoreShows = new ArrayList<>();
                if (courses.size() == 0) {
                    session.setAttribute("scoreShows", null);
                    return "";
                }
                //获取模糊查询后的学生所有sid
                List<Integer> sidByMoHuName = studentService.getSidByMoHuName(value);
                for (Course course : courses) {
                    String cname = course.getCname();
                    Integer cid = course.getCid();
                    List<Student> studentListByCname = studentService.getStudentListByCname(cname);
                    for (Student student : studentListByCname) {
                        Integer sid = student.getSid();
                        //判断该学生是否符合模糊查询后的结果，符合则将该学生信息与当前课程信息封装后存入session域
                        if (sidByMoHuName.contains(sid)) {
                            S_C_Score s_C_Score = s_C_ScoreService.getS_C_ScoreBySidAndCid(sid, cid);
                            ScoreShow scoreShow = new ScoreShow(sid, student.getName(), cname, s_C_Score.getUscore(), s_C_Score.getScore());
                            scoreShow.gettScore();
                            scoreShows.add(scoreShow);
                        }
                    }
                }
                session.setAttribute("scoreShows", scoreShows);
                return "";
            }
        }
    }

    //老师选择修改学生的期末成绩
    public String changeScore(Integer sid, String cname, String newScoreStr,HttpSession session) {
        double newScore = Double.parseDouble(newScoreStr);
        Integer cid = courseService.getCidByCname(cname);
        s_C_ScoreService.changeScore(sid, cid, newScore);
        session.setAttribute("updated",true);
        return "";
    }

    //老师选择修改学生的平时成绩
    public String changeUScore(Integer sid, String cname, String newUScoreStr,HttpSession session) {
        double newUScore = Double.parseDouble(newUScoreStr);
        Integer cid = courseService.getCidByCname(cname);
        s_C_ScoreService.changeUScore(sid, cid, newUScore);
        session.setAttribute("updated",true);
        return "";
    }
}
