package com.example.todolist;

public class Exam {
    private String date;
    private String time;
    private String location;

    public Exam(String date, String time, String location) {
        this.date = date;
        this.time = time;
        this.location = location;
    }

    // Getters
    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getLocation() {
        return location;
    }
}
