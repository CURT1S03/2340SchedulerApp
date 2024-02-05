package com.example.todolist;

// ClassDetail.java
public class ClassDetail {
    private String courseName;
    private String time;
    private String instructor;

    public ClassDetail(String courseName, String time, String instructor) {
        this.courseName = courseName;
        this.time = time;
        this.instructor = instructor;
    }

    // Getters
    public String getCourseName() { return courseName; }
    public String getTime() { return time; }
    public String getInstructor() { return instructor; }

    // Setters
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public void setTime(String time) { this.time = time; }
    public void setInstructor(String instructor) { this.instructor = instructor; }
}
