package com.course.service;

import com.course.model.Student;
import com.course.repository.StudentRepository;
import java.util.List;

/**
 * Student service class containing business logic for student operations
 */
public class StudentService {
    
    private final StudentRepository studentRepository;
    
    public StudentService() {
        this.studentRepository = new StudentRepository();
    }
    
    /**
     * Add a new student with validation
     * @param name Student name
     * @param email Student email
     * @return true if student added successfully, false otherwise
     */
    public boolean addStudent(String name, String email) {
        if (name == null || name.trim().isEmpty()) {
            System.err.println("Student name cannot be empty");
            return false;
        }
        
        if (email == null || email.trim().isEmpty()) {
            System.err.println("Student email cannot be empty");
            return false;
        }
        
        if (!isValidEmail(email)) {
            System.err.println("Invalid email format");
            return false;
        }
        
        Student student = new Student(name.trim(), email.trim());
        return studentRepository.addStudent(student);
    }
    
    /**
     * Get all students
     * @return List of all students
     */
    public List<Student> getAllStudents() {
        return studentRepository.getAllStudents();
    }
    
    /**
     * Get student by ID
     * @param id Student ID
     * @return Student if found, null otherwise
     */
    public Student getStudentById(int id) {
        if (id <= 0) {
            System.err.println("Invalid student ID");
            return null;
        }
        
        return studentRepository.getStudentById(id);
    }
    
    /**
     * Update student information with validation
     * @param id Student ID
     * @param name New name
     * @param email New email
     * @return true if updated successfully, false otherwise
     */
    public boolean updateStudent(int id, String name, String email) {
        if (id <= 0) {
            System.err.println("Invalid student ID");
            return false;
        }
        
        Student existingStudent = studentRepository.getStudentById(id);
        if (existingStudent == null) {
            System.err.println("Student not found");
            return false;
        }
        
        if (name != null && !name.trim().isEmpty()) {
            existingStudent.setName(name.trim());
        }
        
        if (email != null && !email.trim().isEmpty()) {
            if (!isValidEmail(email)) {
                System.err.println("Invalid email format");
                return false;
            }
            existingStudent.setEmail(email.trim());
        }
        
        return studentRepository.updateStudent(existingStudent);
    }
    
    /**
     * Delete student by ID
     * @param id Student ID
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteStudent(int id) {
        if (id <= 0) {
            System.err.println("Invalid student ID");
            return false;
        }
        
        Student student = studentRepository.getStudentById(id);
        if (student == null) {
            System.err.println("Student not found");
            return false;
        }
        
        return studentRepository.deleteStudent(id);
    }
    
    /**
     * Search students by name
     * @param name Name to search for
     * @return List of matching students
     */
    public List<Student> searchStudentsByName(String name) {
        if (name == null || name.trim().isEmpty()) {
            return getAllStudents();
        }
        
        return studentRepository.searchStudentsByName(name.trim());
    }
    
    /**
     * Validate email format
     * @param email Email to validate
     * @return true if valid, false otherwise
     */
    private boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
}
