package com.course.model;

/**
 * Registration model class representing a student-course registration
 */
public class Registration {
    private int id;
    private int studentId;
    private int courseId;
    private String studentName;
    private String courseName;
    
    public Registration() {}
    
    public Registration(int studentId, int courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    public Registration(int id, int studentId, int courseId) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
    }
    
    public Registration(int id, int studentId, int courseId, String studentName, String courseName) {
        this.id = id;
        this.studentId = studentId;
        this.courseId = courseId;
        this.studentName = studentName;
        this.courseName = courseName;
    }
    
    // Getters and Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getStudentId() {
        return studentId;
    }
    
    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
    
    public int getCourseId() {
        return courseId;
    }
    
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
    public String getStudentName() {
        return studentName;
    }
    
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    public String getCourseName() {
        return courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    @Override
    public String toString() {
        return "Registration [ID=" + id + ", Student=" + studentName + 
               " (ID: " + studentId + "), Course=" + courseName + " (ID: " + courseId + ")]";
    }
}
