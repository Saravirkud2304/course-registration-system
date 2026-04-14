package com.course.repository;

import com.course.model.Course;
import com.course.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Course repository class handling database operations for courses
 */
public class CourseRepository {
    
    /**
     * Add a new course to the database
     * @param course Course object to add
     * @return true if successful, false otherwise
     */
    public boolean addCourse(Course course) {
        String sql = "INSERT INTO courses (course_name, duration) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getDuration());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get all courses from the database
     * @return List of courses
     */
    public List<Course> getAllCourses() {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses ORDER BY id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Course course = new Course(
                    rs.getInt("id"),
                    rs.getString("course_name"),
                    rs.getString("duration")
                );
                courses.add(course);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving courses: " + e.getMessage());
        }
        
        return courses;
    }
    
    /**
     * Get course by ID
     * @param id Course ID
     * @return Course object if found, null otherwise
     */
    public Course getCourseById(int id) {
        String sql = "SELECT * FROM courses WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                        rs.getInt("id"),
                        rs.getString("course_name"),
                        rs.getString("duration")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving course: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Update course information
     * @param course Course object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateCourse(Course course) {
        String sql = "UPDATE courses SET course_name = ?, duration = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, course.getCourseName());
            pstmt.setString(2, course.getDuration());
            pstmt.setInt(3, course.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete course by ID
     * @param id Course ID
     * @return true if successful, false otherwise
     */
    public boolean deleteCourse(int id) {
        String sql = "DELETE FROM courses WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting course: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Search courses by name
     * @param courseName Course name to search for
     * @return List of matching courses
     */
    public List<Course> searchCoursesByName(String courseName) {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses WHERE course_name LIKE ? ORDER BY id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + courseName + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course(
                        rs.getInt("id"),
                        rs.getString("course_name"),
                        rs.getString("duration")
                    );
                    courses.add(course);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching courses: " + e.getMessage());
        }
        
        return courses;
    }
}
