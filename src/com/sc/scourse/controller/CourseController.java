package com.sc.scourse.controller;

import com.google.gson.Gson;
import com.sc.scourse.pojo.*;
import com.sc.scourse.service.CourseService;
import com.sc.scourse.service.S_C_ScoreService;
import com.sc.scourse.service.StudentService;
import com.sc.scourse.service.TeacherService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("all")
public class CourseController {

    private CourseService courseService;
    private S_C_ScoreService s_C_ScoreService;
    private StudentService studentService;
    private TeacherService teacherService;

    //获取当前查询后session域中的课程信息
    public String getCourseForStudent(HttpSession session){
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        Object coursesForStudent = session.getAttribute("coursesForStudent");
        Object updated = session.getAttribute("updatedForStudentCourses");
        if(coursesForStudent!=null && updated==null){
            List<Course> courses = (List<Course>) coursesForStudent;
            if(courses.size()==0){
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += courses.size() + ",\"data\":[";
            for (Course course : courses) {
                String courseStr = gson.toJson(course);
                before += courseStr + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        }else{
            session.setAttribute("updatedForStudentCourses",null);
            List<Course> allCourse = courseService.getAllCourse();
            session.setAttribute("coursesForStudent",allCourse);
            if(allCourse.size()==0){
                return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
            }
            before += allCourse.size() + ",\"data\":[";
            for (Course course : allCourse) {
                String courseStr = gson.toJson(course);
                before += courseStr + ",";
            }
            return before.substring(0, before.length() - 1) + "]}";
        }
    }

    //通过筛选查询课程信息，为session域中课程对象赋值
    public String getCourseBySelect(String selectName,String value,HttpSession session) {
        if(selectName.equals("全部") || value.equals("")){
            session.setAttribute("coursesForStudent",null);
            return "";
        }
        List<Course> courses = courseService.getCoursesBySelectCidOrMoHuCname(selectName, value);
        session.setAttribute("coursesForStudent",courses);
        return "";
    }

    //通过当前登录学生信息获取该学生已选课程信息
    public String getSelectedCourse(HttpSession session) {
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        Student currStudent = (Student) session.getAttribute("currStudent");
        Map<Course, AllScore> coursesMap = currStudent.getCourses();
        Set<Course> courses = coursesMap.keySet();
        if(courses.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += courses.size() + ",\"data\":[";
        for (Course course : courses) {
            String c = gson.toJson(course);
            before += c + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //为当前学生添加课程信息
    public String addCourse(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        //获取需要添加的课程号
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        //通过课程号获取课程
        Course courseByCid = courseService.getCourseByCid(cid);
        //将该课程的上课时间与已选课程的上课时间进行比较，若相同则返回添加失败
        String[] timeArr = courseByCid.getTimeArr();
        Student currStudent = (Student) session.getAttribute("currStudent");
        Map<Course, AllScore> coursesMap = currStudent.getCourses();
        Set<Course> courses = coursesMap.keySet();
        for (String time : timeArr) {
            for (Course course : courses) {
                String[] myTimeArr = course.getTimeArr();
                List<String> myTimeList = Arrays.asList(myTimeArr);
                if(myTimeList.contains(time)){
                    return "json:{\"no\":true}";
                } else {
                    continue;
                }
            }
        }
        //上课时间不冲突，继续添加课程
        s_C_ScoreService.addCScore(currStudent.getSid(), cid);
        //根据当前学生学号重新获取具体的学生信息
        Student student = studentService.getStudentBySid(currStudent.getSid());
        //根据当前学生学号重新获取具体的学生信息
        session.setAttribute("currStudent", student);
        //设置学生课程已改变的标记，便于刷新展示数据
        session.setAttribute("updatedForStudentCourses", true);
        return "";
    }

    //学生选择退选选课程
    public String deleteCourse(HttpServletRequest request, HttpSession session) {
        Integer cid = Integer.parseInt(request.getParameter("cid"));
        Student currStudent = (Student) session.getAttribute("currStudent");
        s_C_ScoreService.deleteCScoreBySidAndCid(currStudent.getSid(),cid);
        Student student = studentService.getStudentBySid(currStudent.getSid());//根据当前学生学号重新获取具体的学生信息
        session.setAttribute("currStudent", student);//将学生实体存入session中
        session.setAttribute("updatedForStudentCourses", true);//设置学生课程已改变的标记，便于刷新展示数据
        return "";
    }

    //查询当前学生用户课表
    public String getTimeTableForStudent(HttpSession session){
        Student currStudent =  (Student)session.getAttribute("currStudent");
        List<TimeTable> timeTables = currStudent.getTimeTables();
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if(timeTables.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += timeTables.size() + ",\"data\":[";
        for (TimeTable timeTable : timeTables) {
            String t = gson.toJson(timeTable);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //查询当前老师用户课表
    public String getTimeTableForTeacher(HttpSession session){
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        List<TimeTable> timeTables = currTeacher.getTimeTables();
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if(timeTables.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += timeTables.size() + ",\"data\":[";
        for (TimeTable timeTable : timeTables) {
            String t = gson.toJson(timeTable);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //查询当前登录老师的待审核课程
    public String dataOfNeedAuditCourseByUser(HttpSession session){
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        Integer tid = currTeacher.getTid();
        List<Course> courses = courseService.getNeedAuditCourseByTid(tid);
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if(courses.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += courses.size() + ",\"data\":[";
        for (Course course : courses) {
            String t = gson.toJson(course);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //老师取消待审核课程
    public String cancelNeedAuditCourse(String cname){
        courseService.deleteNeedAuditCourseByCname(cname);
        return "";
    }

    //老师提交课程申请信息
    public String applyCourse(String cname,String time,String address,Integer cap,HttpSession session){
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        Integer tid = currTeacher.getTid();
        time = time.substring(0,time.length()-1);
        if(courseService.getCourseByAddressAndTime(address,time).size()!=0){
            return "json:{\"exist\":true}";
        }
        courseService.addNeedAuditCourse(cname,time,address,cap,tid);
        return "json:{\"exist\":false}";
    }

    //老师查看所授课程信息
    public String dataOfCourseByCurrTeacher(HttpSession session){
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        List<Course> courses = currTeacher.getCourses();
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if(courses.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += courses.size() + ",\"data\":[";
        for (Course course : courses) {
            Integer countOfCourse = s_C_ScoreService.getCountOfCourse(course.getCid());
            course.setSnum(countOfCourse);
            String t = gson.toJson(course);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //老师删除课程
    public String deleteCourseByTeacher(String cname,HttpSession session){
        courseService.deleteCourseByCname(cname);
        Teacher currTeacher = (Teacher) session.getAttribute("currTeacher");
        Teacher teacher = teacherService.getTeacher(currTeacher.getTid());//根据账号的id信息获取具体的教师信息
        session.setAttribute("currTeacher", teacher);//将教师实体存入session中
        return "";
    }

    //获取所有的带审核课程
    public String dateOfAllNeedAuditCourse(){
        List<Course> allNeedAuditCourse = courseService.getAllNeedAuditCourse();
        String before = "json:{\"code\":0,\"msg\":\"\",\"count\":";
        Gson gson = new Gson();
        if(allNeedAuditCourse.size()==0){
            return "json:{\"code\":0,\"msg\":\"\",\"count\":0,\"data\":[]}";
        }
        before += allNeedAuditCourse.size() + ",\"data\":[";
        for (Course course : allNeedAuditCourse) {
            Integer countOfCourse = s_C_ScoreService.getCountOfCourse(course.getCid());
            course.setSnum(countOfCourse);
            String teacherName = teacherService.getTeacherName(course.getTid());
            course.setTeachername(teacherName);
            String t = gson.toJson(course);
            before += t + ",";
        }
        return before.substring(0, before.length() - 1) + "]}";
    }

    //管理员删除待审核课程
    public String denyNeedAuditCourse(String cname){
        courseService.deleteNeedAuditCourseByCname(cname);
        return "";
    }

    //管理员通过待审核课程
    public String agreeNeedAuditCourse(String cname,String time,String address,Integer cap,Integer tid){
        courseService.agreeNeedAuditCourse(cname,time,address,cap,tid);
        return "";
    }

    public String addCourseOfRoot(String cname,String time,String address,Integer cap,Integer tid,HttpSession session){
        if(courseService.getCourseByAddressAndTime(address,time).size()!=0){
            return "json:{\"add\":\"conflict\"}";
        } else if (courseService.getCidByCname(cname) != -1) {
            return "json:{\"add\":\"exist\"}";
        } else if (teacherService.getTeacherName(tid) == null) {
            return "json:{\"add\":\"noteacher\"}";
        }
        courseService.addCourse(cname,time,address,cap,tid);
        session.setAttribute("updatedForStudentCourses",true);
        return "json:{\"add\":\"true\"}";
    }

    //管理员删除课程
    public String deleteCourseOfRoot(String cname,HttpSession session){
        courseService.deleteCourseByCname(cname);
        session.setAttribute("updatedForStudentCourses",true);
        return "";
    }

}
