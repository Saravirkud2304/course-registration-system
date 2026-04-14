package com.course.model;

/**
 * Course model class representing a course entity
 */
public class Course {
    private int id;
    private String courseName;
    private String duration;
    
    public Course() {}
    
    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }
    
    public Course(int id, String courseName, String duration) {
        this.id = id;
        this.courseName = courseName;
        this.duration = duration;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    public String getDuration() {
        return duration;
    }
    
    public void setDuration(String duration) {
        this.duration = duration;
    }
    
    @Override
    public String toString() {
        return "Course [ID=" + id + ", Name=" + courseName + ", Duration=" + duration + "]";
    }
}
