package com.course;

import com.course.controller.StudentController;
import com.course.controller.CourseController;
import com.course.controller.RegistrationController;
import com.course.util.DBConnection;
import java.util.Scanner;

/**
 * Main application class for the Course Registration System
 * This is the entry point of the application
 */
public class MainApp {
    
    private final StudentController studentController;
    private final CourseController courseController;
    private final RegistrationController registrationController;
    private final Scanner scanner;
    
    public MainApp() {
        this.studentController = new StudentController();
        this.courseController = new CourseController();
        this.registrationController = new RegistrationController();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Main method - entry point of the application
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        MainApp app = new MainApp();
        app.start();
    }
    
    /**
     * Start the application
     */
    public void start() {
        System.out.println("=========================================");
        System.out.println("    COURSE REGISTRATION SYSTEM");
        System.out.println("=========================================");
        
        // Test database connection
        if (!DBConnection.testConnection()) {
            System.err.println("Failed to connect to database!");
            System.err.println("Please ensure:");
            System.err.println("1. MySQL server is running");
            System.err.println("2. Database 'course_db' exists");
            System.err.println("3. DB credentials in DBConnection.java are correct");
            System.out.println("\nPress Enter to exit...");
            scanner.nextLine();
            return;
        }
        
        System.out.println("Database connection successful!");
        System.out.println("Welcome to Course Registration System!");
        
        // Main application loop
        while (true) {
            displayMainMenu();
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        studentController.showStudentMenu();
                        break;
                    case 2:
                        courseController.showCourseMenu();
                        break;
                    case 3:
                        registrationController.showRegistrationMenu();
                        break;
                    case 4:
                        System.out.println("\nThank you for using Course Registration System!");
                        System.out.println("Goodbye!");
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
     * Display the main menu
     */
    private void displayMainMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Student Management");
        System.out.println("2. Course Management");
        System.out.println("3. Registration Management");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }
}
