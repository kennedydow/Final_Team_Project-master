package com.course.example.sqlitedemopro;

public class Course {
    private int class_id;
    private String name;
    private String teacher;
    private String time;

    public Course(int ids, String n, String teac, String time){
        this.class_id = ids;
        this.name = n;
        this.teacher = teac;
        this.time = time;
    }
    public Course(String n, String teac, String time){
        this.name = n;
        this.teacher = teac;
        this.time = time;
    }

    public int getclass_id() {
        return this.class_id;
    }
    public void setId(int id) {
        this.class_id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTeacher() {
        return teacher;
    }
    public void setTeacher(String teacher_name) {
        this.teacher = teacher_name;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time2) {
        this.time = time2;
    }



}
