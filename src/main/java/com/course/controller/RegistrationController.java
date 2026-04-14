package com.course.controller;

import com.course.model.Registration;
import com.course.model.Student;
import com.course.model.Course;
import com.course.service.RegistrationService;
import com.course.service.StudentService;
import com.course.service.CourseService;
import java.util.List;
import java.util.Scanner;

/**
 * Registration controller class handling user interactions for registration operations
 */
public class RegistrationController {
    
    private final RegistrationService registrationService;
    private final StudentService studentService;
    private final CourseService courseService;
    private final Scanner scanner;
    
    public RegistrationController() {
        this.registrationService = new RegistrationService();
        this.studentService = new StudentService();
        this.courseService = new CourseService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Display registration management menu
     */
    public void showRegistrationMenu() {
        while (true) {
            System.out.println("\n=== REGISTRATION MANAGEMENT ===");
            System.out.println("1. Register Student for Course");
            System.out.println("2. View All Registrations");
            System.out.println("3. View Registrations by Student");
            System.out.println("4. View Registrations by Course");
            System.out.println("5. Cancel Registration");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        registerStudent();
                        break;
                    case 2:
                        viewAllRegistrations();
                        break;
                    case 3:
                        viewRegistrationsByStudent();
                        break;
                    case 4:
                        viewRegistrationsByCourse();
                        break;
                    case 5:
                        cancelRegistration();
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
    
    /**
     * Register a student for a course
     */
    private void registerStudent() {
        System.out.println("\n--- REGISTER STUDENT FOR COURSE ---");
        
        // Display available students
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students available. Please add students first.");
            return;
        }
        
        System.out.println("Available Students:");
        System.out.println("ID\tName\t\tEmail");
        System.out.println("----------------------------------------");
        for (Student student : students) {
            System.out.printf("%d\t%s\t\t%s%n", 
                student.getId(), student.getName(), student.getEmail());
        }
        
        System.out.print("Enter student ID: ");
        int studentId = Integer.parseInt(scanner.nextLine());
        
        // Display available courses
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available. Please add courses first.");
            return;
        }
        
        System.out.println("\nAvailable Courses:");
        System.out.println("ID\tCourse Name\t\tDuration");
        System.out.println("----------------------------------------");
        for (Course course : courses) {
            System.out.printf("%d\t%-20s\t%s%n", 
                course.getId(), course.getCourseName(), course.getDuration());
        }
        
        System.out.print("Enter course ID: ");
        int courseId = Integer.parseInt(scanner.nextLine());
        
        if (registrationService.registerStudent(studentId, courseId)) {
            System.out.println("Student registered successfully!");
        } else {
            System.out.println("Failed to register student. Please check the input and try again.");
        }
    }
    
    /**
     * View all registrations
     */
    private void viewAllRegistrations() {
        System.out.println("\n--- ALL REGISTRATIONS ---");
        List<Registration> registrations = registrationService.getAllRegistrations();
        
        if (registrations.isEmpty()) {
            System.out.println("No registrations found.");
        } else {
            System.out.println("ID\tStudent Name\t\tCourse Name");
            System.out.println("--------------------------------------------------");
            for (Registration registration : registrations) {
                System.out.printf("%d\t%-20s\t%s%n", 
                    registration.getId(), 
                    registration.getStudentName(), 
                    registration.getCourseName());
            }
        }
    }
    
    /**
     * View registrations by student
     */
    private void viewRegistrationsByStudent() {
        System.out.println("\n--- VIEW REGISTRATIONS BY STUDENT ---");
        
        List<Student> students = studentService.getAllStudents();
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        
        System.out.println("Available Students:");
        System.out.println("ID\tName");
        System.out.println("------------------------");
        for (Student student : students) {
            System.out.printf("%d\t%s%n", student.getId(), student.getName());
        }
        
        System.out.print("Enter student ID: ");
        try {
            int studentId = Integer.parseInt(scanner.nextLine());
            
            List<Registration> registrations = registrationService.getRegistrationsByStudentId(studentId);
            
            if (registrations == null || registrations.isEmpty()) {
                System.out.println("No registrations found for this student.");
            } else {
                Student student = studentService.getStudentById(studentId);
                System.out.println("Registrations for " + student.getName() + ":");
                System.out.println("ID\tCourse Name");
                System.out.println("------------------------");
                for (Registration registration : registrations) {
                    System.out.printf("%d\t%s%n", 
                        registration.getId(), registration.getCourseName());
                }
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID.");
        }
    }
    
    /**
     * View registrations by course
     */
    private void viewRegistrationsByCourse() {
        System.out.println("\n--- VIEW REGISTRATIONS BY COURSE ---");
        
        List<Course> courses = courseService.getAllCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
            return;
        }
        
        System.out.println("Available Courses:");
        System.out.println("ID\tCourse Name");
        System.out.println("------------------------");
        for (Course course : courses) {
            System.out.printf("%d\t%s%n", course.getId(), course.getCourseName());
        }
        
        System.out.print("Enter course ID: ");
        try {
            int courseId = Integer.parseInt(scanner.nextLine());
            
            List<Registration> registrations = registrationService.getRegistrationsByCourseId(courseId);
            
            if (registrations == null || registrations.isEmpty()) {
                System.out.println("No registrations found for this course.");
            } else {
                Course course = courseService.getCourseById(courseId);
                System.out.println("Students registered for " + course.getCourseName() + ":");
                System.out.println("ID\tStudent Name");
                System.out.println("------------------------");
                for (Registration registration : registrations) {
                    System.out.printf("%d\t%s%n", 
                        registration.getId(), registration.getStudentName());
                }
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid course ID.");
        }
    }
    
    /**
     * Cancel a registration
     */
    private void cancelRegistration() {
        System.out.println("\n--- CANCEL REGISTRATION ---");
        
        List<Registration> registrations = registrationService.getAllRegistrations();
        if (registrations.isEmpty()) {
            System.out.println("No registrations available to cancel.");
            return;
        }
        
        System.out.println("Current Registrations:");
        System.out.println("ID\tStudent Name\t\tCourse Name");
        System.out.println("--------------------------------------------------");
        for (Registration registration : registrations) {
            System.out.printf("%d\t%-20s\t%s%n", 
                registration.getId(), 
                registration.getStudentName(), 
                registration.getCourseName());
        }
        
        System.out.print("Enter registration ID to cancel: ");
        try {
            int registrationId = Integer.parseInt(scanner.nextLine());
            
            Registration registration = registrations.stream()
                .filter(r -> r.getId() == registrationId)
                .findFirst()
                .orElse(null);
            
            if (registration == null) {
                System.out.println("Registration not found.");
                return;
            }
            
            System.out.println("Registration to cancel:");
            System.out.println("Student: " + registration.getStudentName());
            System.out.println("Course: " + registration.getCourseName());
            
            System.out.print("Are you sure you want to cancel this registration? (y/N): ");
            String confirmation = scanner.nextLine().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                if (registrationService.deleteRegistration(registrationId)) {
                    System.out.println("Registration cancelled successfully!");
                } else {
                    System.out.println("Failed to cancel registration.");
                }
            } else {
                System.out.println("Cancellation cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid registration ID.");
        }
    }
}
