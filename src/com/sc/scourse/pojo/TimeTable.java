package com.sc.scourse.pojo;

//课程表类
public class TimeTable {
    private String period;
    private String timeMonday;
    private String timeTuesday;
    private String timeWednesday;
    private String timeThursday;
    private String timeFriday;
    private String timeSaturday;
    private String timeSunday;

    public TimeTable(String period) {
        this.period = period;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getTimeMonday() {
        return timeMonday;
    }

    public void setTimeMonday(String timeMonday) {
        this.timeMonday = timeMonday;
    }

    public String getTimeTuesday() {
        return timeTuesday;
    }

    public void setTimeTuesday(String timeTuesday) {
        this.timeTuesday = timeTuesday;
    }

    public String getTimeWednesday() {
        return timeWednesday;
    }

    public void setTimeWednesday(String timeWednesday) {
        this.timeWednesday = timeWednesday;
    }

    public String getTimeThursday() {
        return timeThursday;
    }

    public void setTimeThursday(String timeThursday) {
        this.timeThursday = timeThursday;
    }

    public String getTimeFriday() {
        return timeFriday;
    }

    public void setTimeFriday(String timeFriday) {
        this.timeFriday = timeFriday;
    }

    public String getTimeSaturday() {
        return timeSaturday;
    }

    public void setTimeSaturday(String timeSaturday) {
        this.timeSaturday = timeSaturday;
    }

    public String getTimeSunday() {
        return timeSunday;
    }

    public void setTimeSunday(String timeSunday) {
        this.timeSunday = timeSunday;
    }
}
