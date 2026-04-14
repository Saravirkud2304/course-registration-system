package com.course.service;

import com.course.model.Course;
import com.course.repository.CourseRepository;
import java.util.List;

/**
 * Course service class containing business logic for course operations
 */
public class CourseService {
    
    private final CourseRepository courseRepository;
    
    public CourseService() {
        this.courseRepository = new CourseRepository();
    }
    
    /**
     * Add a new course with validation
     * @param courseName Course name
     * @param duration Course duration
     * @return true if course added successfully, false otherwise
     */
    public boolean addCourse(String courseName, String duration) {
        if (courseName == null || courseName.trim().isEmpty()) {
            System.err.println("Course name cannot be empty");
            return false;
        }
        
        if (duration == null || duration.trim().isEmpty()) {
            System.err.println("Course duration cannot be empty");
            return false;
        }
        
        Course course = new Course(courseName.trim(), duration.trim());
        return courseRepository.addCourse(course);
    }
    
    /**
     * Get all courses
     * @return List of all courses
     */
    public List<Course> getAllCourses() {
        return courseRepository.getAllCourses();
    }
    
    /**
     * Get course by ID
     * @param id Course ID
     * @return Course if found, null otherwise
     */
    public Course getCourseById(int id) {
        if (id <= 0) {
            System.err.println("Invalid course ID");
            return null;
        }
        
        return courseRepository.getCourseById(id);
    }
    
    /**
     * Update course information with validation
     * @param id Course ID
     * @param courseName New course name
     * @param duration New duration
     * @return true if updated successfully, false otherwise
     */
    public boolean updateCourse(int id, String courseName, String duration) {
        if (id <= 0) {
            System.err.println("Invalid course ID");
            return false;
        }
        
        Course existingCourse = courseRepository.getCourseById(id);
        if (existingCourse == null) {
            System.err.println("Course not found");
            return false;
        }
        
        if (courseName != null && !courseName.trim().isEmpty()) {
            existingCourse.setCourseName(courseName.trim());
        }
        
        if (duration != null && !duration.trim().isEmpty()) {
            existingCourse.setDuration(duration.trim());
        }
        
        return courseRepository.updateCourse(existingCourse);
    }
    
    /**
     * Delete course by ID
     * @param id Course ID
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteCourse(int id) {
        if (id <= 0) {
            System.err.println("Invalid course ID");
            return false;
        }
        
        Course course = courseRepository.getCourseById(id);
        if (course == null) {
            System.err.println("Course not found");
            return false;
        }
        
        return courseRepository.deleteCourse(id);
    }
    
    /**
     * Search courses by name
     * @param courseName Course name to search for
     * @return List of matching courses
     */
    public List<Course> searchCoursesByName(String courseName) {
        if (courseName == null || courseName.trim().isEmpty()) {
            return getAllCourses();
        }
        
        return courseRepository.searchCoursesByName(courseName.trim());
    }
}
