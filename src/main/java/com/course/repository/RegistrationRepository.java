package com.course.repository;

import com.course.model.Registration;
import com.course.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Registration repository class handling database operations for registrations
 */
public class RegistrationRepository {
    
    /**
     * Register a student for a course
     * @param registration Registration object
     * @return true if successful, false otherwise
     */
    public boolean registerStudent(Registration registration) {
        String sql = "INSERT INTO registrations (student_id, course_id) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, registration.getStudentId());
            pstmt.setInt(2, registration.getCourseId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error registering student: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get all registrations from the database
     * @return List of registrations
     */
    public List<Registration> getAllRegistrations() {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT r.id, r.student_id, r.course_id, s.name as student_name, " +
                    "c.course_name FROM registrations r " +
                    "JOIN students s ON r.student_id = s.id " +
                    "JOIN courses c ON r.course_id = c.id " +
                    "ORDER BY r.id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Registration registration = new Registration(
                    rs.getInt("id"),
                    rs.getInt("student_id"),
                    rs.getInt("course_id"),
                    rs.getString("student_name"),
                    rs.getString("course_name")
                );
                registrations.add(registration);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving registrations: " + e.getMessage());
        }
        
        return registrations;
    }
    
    /**
     * Get registrations by student ID
     * @param studentId Student ID
     * @return List of registrations for the student
     */
    public List<Registration> getRegistrationsByStudentId(int studentId) {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT r.id, r.student_id, r.course_id, s.name as student_name, " +
                    "c.course_name FROM registrations r " +
                    "JOIN students s ON r.student_id = s.id " +
                    "JOIN courses c ON r.course_id = c.id " +
                    "WHERE r.student_id = ? ORDER BY r.id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Registration registration = new Registration(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("student_name"),
                        rs.getString("course_name")
                    );
                    registrations.add(registration);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving registrations by student: " + e.getMessage());
        }
        
        return registrations;
    }
    
    /**
     * Get registrations by course ID
     * @param courseId Course ID
     * @return List of registrations for the course
     */
    public List<Registration> getRegistrationsByCourseId(int courseId) {
        List<Registration> registrations = new ArrayList<>();
        String sql = "SELECT r.id, r.student_id, r.course_id, s.name as student_name, " +
                    "c.course_name FROM registrations r " +
                    "JOIN students s ON r.student_id = s.id " +
                    "JOIN courses c ON r.course_id = c.id " +
                    "WHERE r.course_id = ? ORDER BY r.id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, courseId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Registration registration = new Registration(
                        rs.getInt("id"),
                        rs.getInt("student_id"),
                        rs.getInt("course_id"),
                        rs.getString("student_name"),
                        rs.getString("course_name")
                    );
                    registrations.add(registration);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving registrations by course: " + e.getMessage());
        }
        
        return registrations;
    }
    
    /**
     * Check if a student is already registered for a course
     * @param studentId Student ID
     * @param courseId Course ID
     * @return true if already registered, false otherwise
     */
    public boolean isStudentRegistered(int studentId, int courseId) {
        String sql = "SELECT COUNT(*) FROM registrations WHERE student_id = ? AND course_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error checking registration: " + e.getMessage());
        }
        
        return false;
    }
    
    /**
     * Delete registration by ID
     * @param id Registration ID
     * @return true if successful, false otherwise
     */
    public boolean deleteRegistration(int id) {
        String sql = "DELETE FROM registrations WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting registration: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete registration by student and course
     * @param studentId Student ID
     * @param courseId Course ID
     * @return true if successful, false otherwise
     */
    public boolean deleteRegistration(int studentId, int courseId) {
        String sql = "DELETE FROM registrations WHERE student_id = ? AND course_id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, courseId);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting registration: " + e.getMessage());
            return false;
        }
    }
}
