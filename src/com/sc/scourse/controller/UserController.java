package com.sc.scourse.controller;

import com.google.gson.Gson;
import com.sc.scourse.pojo.*;
import com.sc.scourse.service.StudentService;
import com.sc.scourse.service.TeacherService;
import com.sc.scourse.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Set;

public class UserController {
    private UserService userService;
    private StudentService studentService;
    private TeacherService teacherService;

    //用于用户登录验证，以及用户登录成功后的操作
    public String login(String acount, String pwd, String role, HttpSession session) {
        User user = userService.login(acount, pwd);
        if (user != null && user.getRole().equals(role)) {//判断能否查询到已有帐号以及角色是否一致
            //若账号存在，则将验证账号密码的标志设为false，该标志用于前端判断是否需要显示提示信息
            System.out.println("获取成功");
            if (user.getRole().equals("1")) {//登录账号角色为学生
                System.out.println("学生登陆成功");
                Student student = studentService.getStudentBySid(user.getId());//根据账号的id信息获取具体的学生信息
                session.setAttribute("currStudent", student);//将学生实体存入session中
                return "json:{\"login\":\"student\"}";
            } else if (user.getRole().equals("2")) {//登录账号角色为教师
                System.out.println("老师登录成功");
                Teacher teacher = teacherService.getTeacher(user.getId());//根据账号的id信息获取具体的教师信息
                session.setAttribute("currTeacher", teacher);//将教师实体存入session中
                session.setAttribute("changeUser",true);
                return "json:{\"login\":\"teacher\"}";
            } else {
                System.out.println("管理员登录成功");
                return "json:{\"login\":\"root\"}";
            }
        } else {
            return "json:{\"login\":\"false\"}";
        }
    }

    //用于判断用户输入的账号密码是否正确
    public String showErro(HttpSession session) {
        Boolean showErro = (Boolean) session.getAttribute("showErro");
        if (showErro == null || showErro == false) {//尝试获取提示信息，若提示信息存在，则返回信息，用于页面显示提示信息
            return "json:{\"showErroFlag\":false}";
        } else {
            session.setAttribute("showErro",false);
            return "json:{\"showErroFlag\":true}";
        }
    }

    public String textData(HttpSession session) {
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        Student currStudent = (Student)session.getAttribute("currStudent");
        Map<Course, AllScore> coursesMap = currStudent.getCourses();
        Set<Course> courses = coursesMap.keySet();
        before += courses.size() + ",\"data\":[";
        for (Course course : courses) {
            String c = gson.toJson(course);
            before+=c+",";
        }
        return before.substring(0,before.length()-1) + "]}";
    }

    //用于学生用户修改密码
    public String changePasswordForStudent(String oldPassword,String newPassword,HttpSession session){
        Student currStudent = (Student) session.getAttribute("currStudent");
        Integer sid = currStudent.getSid();
        User currUser = userService.getUserById(sid);
        if(!currUser.getPwd().equals(oldPassword)){
            return "json:{\"pwdIsRight\":false}";
        }
        userService.changePwdById(sid,newPassword);
        return "json:{\"pwdIsRight\":true}";
    }

    //用于教师用户修改密码
    public String changePasswordForTeacher(String oldPassword,String newPassword,HttpSession session){
        //获取当前用户的实体对象
        Teacher teacher = (Teacher) session.getAttribute("currTeacher");
        //获取当前教师的学工号
        Integer tid = teacher.getTid();
        User currUser = userService.getUserById(tid);
        //判断所填写的原密码是否和数据库中的密码相符合
        if(!currUser.getPwd().equals(oldPassword)){
            return "json:{\"pwdIsRight\":false}";
        }
        //所填写的原密码是否和数据库中的密码相符合,进行修改密码
        userService.changePwdById(tid,newPassword);
        return "json:{\"pwdIsRight\":true}";
    }


}
