package com.sc.scourse.controller;

import com.google.gson.Gson;
import com.sc.scourse.dao.UserDao;
import com.sc.scourse.pojo.Student;
import com.sc.scourse.pojo.TimeTable;
import com.sc.scourse.pojo.User;
import com.sc.scourse.service.StudentService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class StudentController {
    private StudentService studentService;
    private UserDao userDao;

    //当老师点击课程表，得到对应课程名，从而获取该课程的学生列表,存入session域
    //或者管理员点击课程列表上的学生名单，得到对应课程名，从而获取该课程的学生列表,存入session域
    public String getStudentsBycname(String cname, HttpSession session) {
        if (cname == null) {
            return "";
        }
        //因当老师点击课程表时，对应课程名称格式为 "高等数学上 6教-502" 所以需要进行格式变化改为"高等数学上"
        if(cname.indexOf(" ")!=-1){
            cname = cname.substring(0, cname.indexOf(" "));
        }
        //若当前为管理员点击至此，则课程名不用进行改变
        List<Student> studentListByCname = studentService.getStudentListByCname(cname);
        session.setAttribute("studentListByCname", studentListByCname);
        return "";
    }

    //将之前获取到的某课程的学生列表从session域中取出，转换为json格式的数据传送至页面
    public String getDataOfStudents(HttpSession session) {
        List<Student> studentListByCname = (List<Student>) session.getAttribute("studentListByCname");
        //获取后将session域中的studentListByCname清空
        session.setAttribute("studentListByCname", new ArrayList());
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if (studentListByCname.size() == 0) {
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += studentListByCname.size() + ",\"data\":[";
        for (Student student : studentListByCname) {
            String t = gson.toJson(student);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //管理员界面查看学生用户的详细信息
    public String dataOfStudentBySelected(HttpSession session) {
        //首先从session域中获取studentDetail以及修改的标记updatedStudentUser
        Object studentDetail = session.getAttribute("studentDetail");
        Object updated = session.getAttribute("updatedStudentUser");
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        //若studentDetail不为空且updatedStudentUser为空则代表已经进行查询并且没有进行修改，则将域中的studentDetail作为数据送至前端页面
        if (studentDetail != null && updated == null) {
            List<Student> studentDetailList = (List<Student>) studentDetail;
            if (studentDetailList.size() == 0) {
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += studentDetailList.size() + ",\"data\":[";
            for (Student student : studentDetailList) {
                String t = gson.toJson(student);
                before += t + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        } else {
            session.setAttribute("updatedStudentUser", null);
            List<Student> allStudents = studentService.getStudentListBySelectNameAndValue("全部", "");
            //将查询到的信息存入session域方便下一次查看使用
            session.setAttribute("studentDetail", allStudents);
            if (allStudents.size() == 0) {
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += allStudents.size() + ",\"data\":[";
            for (Student student : allStudents) {
                String t = gson.toJson(student);
                before += t + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        }
    }

    //管理员界面点击筛选学生用户展示的信息
    public String selectStudentDetail(String selectName, String value, HttpSession session) {
        List<Student> studentListBySelectNameAndValue = studentService.getStudentListBySelectNameAndValue(selectName, value);
        session.setAttribute("studentDetail", studentListBySelectNameAndValue);
        return "";
    }

    //对学生信息进行删除
    public String deleteStudent(Integer sid, HttpSession session) {
        studentService.deleteStudent(sid);
        session.setAttribute("updatedStudentUser", true);
        return "";
    }

    //对学生信息进行修改
    public String updateStudent(Integer sid, String name, String sex, String major, String grade, String sclass , String acount, String pwd,HttpSession session) {
        studentService.updateStudent(sid,name,sex,major,grade,sclass,acount,pwd);
        session.setAttribute("updatedStudentUser", true);
        return "";
    }

    //对学生信息进行添加
    public String addStudent(Integer sid,String name, String sex, String major, String grade, String sclass,String pwd,HttpSession session){
        boolean ok = studentService.addStudent(sid, name, sex, major, grade, sclass, pwd);
        if(ok == false){
            return "json:{\"add\":false}";
        }else{
            session.setAttribute("updatedStudentUser", true);
            return "json:{\"add\":true}";
        }
    }
}
