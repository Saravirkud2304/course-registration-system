package com.course.repository;

import com.course.model.Student;
import com.course.util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Student repository class handling database operations for students
 */
public class StudentRepository {
    
    /**
     * Add a new student to the database
     * @param student Student object to add
     * @return true if successful, false otherwise
     */
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, email) VALUES (?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Get all students from the database
     * @return List of students
     */
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students ORDER BY id";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Student student = new Student(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email")
                );
                students.add(student);
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving students: " + e.getMessage());
        }
        
        return students;
    }
    
    /**
     * Get student by ID
     * @param id Student ID
     * @return Student object if found, null otherwise
     */
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                    );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error retrieving student: " + e.getMessage());
        }
        
        return null;
    }
    
    /**
     * Update student information
     * @param student Student object with updated information
     * @return true if successful, false otherwise
     */
    public boolean updateStudent(Student student) {
        String sql = "UPDATE students SET name = ?, email = ? WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getEmail());
            pstmt.setInt(3, student.getId());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating student: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Delete student by ID
     * @param id Student ID
     * @return true if successful, false otherwise
     */
    public boolean deleteStudent(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Search students by name
     * @param name Name to search for
     * @return List of matching students
     */
    public List<Student> searchStudentsByName(String name) {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students WHERE name LIKE ? ORDER BY id";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, "%" + name + "%");
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email")
                    );
                    students.add(student);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching students: " + e.getMessage());
        }
        
        return students;
    }
}
