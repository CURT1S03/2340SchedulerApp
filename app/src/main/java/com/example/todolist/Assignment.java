package com.example.todolist;

public class Assignment {
    private String id;
    private String title;
    private String dueDate;
    private String associatedClass;

    public Assignment(String id, String title, String dueDate, String associatedClass) {
        this.id = id;
        this.title = title;
        this.dueDate = dueDate;
        this.associatedClass = associatedClass;
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public String getAssociatedClass() { return associatedClass; }
    public void setAssociatedClass(String associatedClass) { this.associatedClass = associatedClass; }
}
