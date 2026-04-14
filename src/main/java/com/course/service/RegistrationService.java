package com.course.service;

import com.course.model.Registration;
import com.course.repository.RegistrationRepository;
import com.course.repository.StudentRepository;
import com.course.repository.CourseRepository;
import java.util.List;

/**
 * Registration service class containing business logic for registration operations
 */
public class RegistrationService {
    
    private final RegistrationRepository registrationRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    
    public RegistrationService() {
        this.registrationRepository = new RegistrationRepository();
        this.studentRepository = new StudentRepository();
        this.courseRepository = new CourseRepository();
    }
    
    /**
     * Register a student for a course with validation
     * @param studentId Student ID
     * @param courseId Course ID
     * @return true if registered successfully, false otherwise
     */
    public boolean registerStudent(int studentId, int courseId) {
        if (studentId <= 0) {
            System.err.println("Invalid student ID");
            return false;
        }
        
        if (courseId <= 0) {
            System.err.println("Invalid course ID");
            return false;
        }
        
        // Check if student exists
        if (studentRepository.getStudentById(studentId) == null) {
            System.err.println("Student not found");
            return false;
        }
        
        // Check if course exists
        if (courseRepository.getCourseById(courseId) == null) {
            System.err.println("Course not found");
            return false;
        }
        
        // Check if already registered
        if (registrationRepository.isStudentRegistered(studentId, courseId)) {
            System.err.println("Student is already registered for this course");
            return false;
        }
        
        Registration registration = new Registration(studentId, courseId);
        return registrationRepository.registerStudent(registration);
    }
    
    /**
     * Get all registrations
     * @return List of all registrations
     */
    public List<Registration> getAllRegistrations() {
        return registrationRepository.getAllRegistrations();
    }
    
    /**
     * Get registrations by student ID
     * @param studentId Student ID
     * @return List of registrations for the student
     */
    public List<Registration> getRegistrationsByStudentId(int studentId) {
        if (studentId <= 0) {
            System.err.println("Invalid student ID");
            return null;
        }
        
        return registrationRepository.getRegistrationsByStudentId(studentId);
    }
    
    /**
     * Get registrations by course ID
     * @param courseId Course ID
     * @return List of registrations for the course
     */
    public List<Registration> getRegistrationsByCourseId(int courseId) {
        if (courseId <= 0) {
            System.err.println("Invalid course ID");
            return null;
        }
        
        return registrationRepository.getRegistrationsByCourseId(courseId);
    }
    
    /**
     * Delete registration by ID
     * @param id Registration ID
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteRegistration(int id) {
        if (id <= 0) {
            System.err.println("Invalid registration ID");
            return false;
        }
        
        return registrationRepository.deleteRegistration(id);
    }
    
    /**
     * Delete registration by student and course
     * @param studentId Student ID
     * @param courseId Course ID
     * @return true if deleted successfully, false otherwise
     */
    public boolean deleteRegistration(int studentId, int courseId) {
        if (studentId <= 0) {
            System.err.println("Invalid student ID");
            return false;
        }
        
        if (courseId <= 0) {
            System.err.println("Invalid course ID");
            return false;
        }
        
        return registrationRepository.deleteRegistration(studentId, courseId);
    }
    
    /**
     * Check if a student is registered for a course
     * @param studentId Student ID
     * @param courseId Course ID
     * @return true if registered, false otherwise
     */
    public boolean isStudentRegistered(int studentId, int courseId) {
        if (studentId <= 0 || courseId <= 0) {
            return false;
        }
        
        return registrationRepository.isStudentRegistered(studentId, courseId);
    }
}
