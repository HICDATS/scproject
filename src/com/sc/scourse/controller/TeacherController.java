package com.sc.scourse.controller;

import com.google.gson.Gson;
import com.sc.scourse.pojo.Teacher;
import com.sc.scourse.service.TeacherService;

import javax.servlet.http.HttpSession;
import java.util.List;

public class TeacherController {
    private TeacherService teacherService;
    //管理员界面查看教师用户的详细信息
    public String dataOfTeacherBySelected(HttpSession session) {
        //首先从session域中获取teacherDetail以及修改的标记updatedTeacherUser
        Object teacherDetail = session.getAttribute("teacherDetail");
        Object updated = session.getAttribute("updatedTeacherUser");
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        //若studentDetail不为空且updatedStudentUser为空则代表已经进行查询并且没有进行修改，则将域中的studentDetail作为数据送至前端页面
        if (teacherDetail != null && updated == null) {
            List<Teacher> teacherDetailList = (List<Teacher>) teacherDetail;
            if (teacherDetailList.size() == 0) {
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += teacherDetailList.size() + ",\"data\":[";
            for (Teacher teacher : teacherDetailList) {
                String t = gson.toJson(teacher);
                before += t + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        } else {
            session.setAttribute("updatedTeacherUser", null);
            List<Teacher> allTeachers = teacherService.getTeacherListBySelectNameAndValue("全部", "");
            //将查询到的信息存入session域方便下一次查看使用
            session.setAttribute("teacherDetail", allTeachers);
            if (allTeachers.size() == 0) {
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += allTeachers.size() + ",\"data\":[";
            for (Teacher teacher : allTeachers) {
                String t = gson.toJson(teacher);
                before += t + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        }
    }

    //管理员界面点击筛选学生用户展示的信息
    public String selectTeacherDetail(String selectName, String value, HttpSession session) {
        List<Teacher> teacherListBySelectNameAndValue = teacherService.getTeacherListBySelectNameAndValue(selectName, value);
        session.setAttribute("teacherDetail", teacherListBySelectNameAndValue);
        return "";
    }

    //对教师信息进行删除
    public String deleteTeacher(Integer tid, HttpSession session) {
        teacherService.deleteTeacher(tid);
        session.setAttribute("updatedTeacherUser", true);
        return "";
    }

    //对教师信息进行修改
    public String updateTeacher(Integer tid, String name, String sex, String college, String jobTitle , String acount, String pwd,HttpSession session) {
        teacherService.updateTeacher(tid,name,sex,college,jobTitle,acount,pwd);
        session.setAttribute("updatedTeacherUser", true);
        return "";
    }


    //对教师信息进行添加
    public String addTeacher(Integer tid,String name, String sex, String college, String jobTitle,String pwd,HttpSession session){
        boolean ok = teacherService.addTeacher(tid, name, sex, college, jobTitle, pwd);
        if(ok == false){
            return "json:{\"add\":false}";
        }else{
            session.setAttribute("updatedTeacherUser", true);
            return "json:{\"add\":true}";
        }
    }
}
