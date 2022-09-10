package com.sc.scourse.utils;

import com.sc.scourse.pojo.Course;
import com.sc.scourse.pojo.TimeTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class TimeTableUtil {
    public static List<TimeTable> getTimeTableListByCourses(Set<Course> courses){
        List<TimeTable> timeTables = new ArrayList<>();
        timeTables.add(new TimeTable("1-2节"));
        timeTables.add(new TimeTable("3-4节"));
        timeTables.add(new TimeTable("5-6节"));
        timeTables.add(new TimeTable("7-8节"));
        timeTables.add(new TimeTable("9-10节"));
        timeTables.add(new TimeTable("11-12节"));
        for (Course course : courses) {
            String[] timeArr = course.getTimeArr();
            for (String time : timeArr) {
                String period = time.substring(4,7);
                String week = time.substring(0,3);
                switch (period) {
                    case "1-2":{
                        switch (week){
                            case "星期一":timeTables.get(0).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(0).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(0).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(0).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(0).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(0).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(0).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                    case "3-4":{
                        switch (week){
                            case "星期一":timeTables.get(1).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(1).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(1).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(1).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(1).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(1).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(1).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                    case "5-6":{
                        switch (week){
                            case "星期一":timeTables.get(2).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(2).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(2).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(2).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(2).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(2).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(2).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                    case "7-8":{
                        switch (week){
                            case "星期一":timeTables.get(3).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(3).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(3).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(3).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(3).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(3).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(3).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                    case "9-10":{
                        switch (week){
                            case "星期一":timeTables.get(4).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(4).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(4).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(4).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(4).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(4).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(4).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                    case "11-12":{
                        switch (week){
                            case "星期一":timeTables.get(5).setTimeMonday(course.getCname()+" "+course.getAddress());break;
                            case "星期二":timeTables.get(5).setTimeTuesday(course.getCname()+" "+course.getAddress());break;
                            case "星期三":timeTables.get(5).setTimeWednesday(course.getCname()+" "+course.getAddress());break;
                            case "星期四":timeTables.get(5).setTimeThursday(course.getCname()+" "+course.getAddress());break;
                            case "星期五":timeTables.get(5).setTimeFriday(course.getCname()+" "+course.getAddress());break;
                            case "星期六":timeTables.get(5).setTimeSaturday(course.getCname()+" "+course.getAddress());break;
                            case "星期日":timeTables.get(5).setTimeSunday(course.getCname()+" "+course.getAddress());break;
                        }
                        break;
                    }
                }
            }
        }
        return timeTables;
    }
}
