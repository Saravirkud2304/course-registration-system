package com.course.controller;

import com.course.model.Course;
import com.course.service.CourseService;
import java.util.List;
import java.util.Scanner;

/**
 * Course controller class handling user interactions for course operations
 */
public class CourseController {
    
    private final CourseService courseService;
    private final Scanner scanner;
    
    public CourseController() {
        this.courseService = new CourseService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Display course management menu
     */
    public void showCourseMenu() {
        while (true) {
            System.out.println("\n=== COURSE MANAGEMENT ===");
            System.out.println("1. Add Course");
            System.out.println("2. View All Courses");
            System.out.println("3. Search Course by Name");
            System.out.println("4. Update Course");
            System.out.println("5. Delete Course");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        addCourse();
                        break;
                    case 2:
                        viewAllCourses();
                        break;
                    case 3:
                        searchCourse();
                        break;
                    case 4:
                        updateCourse();
                        break;
                    case 5:
                        deleteCourse();
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
     * Add a new course
     */
    private void addCourse() {
        System.out.println("\n--- ADD NEW COURSE ---");
        
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        
        System.out.print("Enter course duration: ");
        String duration = scanner.nextLine();
        
        if (courseService.addCourse(courseName, duration)) {
            System.out.println("Course added successfully!");
        } else {
            System.out.println("Failed to add course. Please check the input and try again.");
        }
    }
    
    /**
     * View all courses
     */
    private void viewAllCourses() {
        System.out.println("\n--- ALL COURSES ---");
        List<Course> courses = courseService.getAllCourses();
        
        if (courses.isEmpty()) {
            System.out.println("No courses found.");
        } else {
            System.out.println("ID\tCourse Name\t\tDuration");
            System.out.println("----------------------------------------");
            for (Course course : courses) {
                System.out.printf("%d\t%-20s\t%s%n", 
                    course.getId(), 
                    course.getCourseName(), 
                    course.getDuration());
            }
        }
    }
    
    /**
     * Search course by name
     */
    private void searchCourse() {
        System.out.println("\n--- SEARCH COURSE ---");
        System.out.print("Enter course name to search: ");
        String courseName = scanner.nextLine();
        
        List<Course> courses = courseService.searchCoursesByName(courseName);
        
        if (courses.isEmpty()) {
            System.out.println("No courses found with the given name.");
        } else {
            System.out.println("Search Results:");
            System.out.println("ID\tCourse Name\t\tDuration");
            System.out.println("----------------------------------------");
            for (Course course : courses) {
                System.out.printf("%d\t%-20s\t%s%n", 
                    course.getId(), 
                    course.getCourseName(), 
                    course.getDuration());
            }
        }
    }
    
    /**
     * Update course information
     */
    private void updateCourse() {
        System.out.println("\n--- UPDATE COURSE ---");
        System.out.print("Enter course ID to update: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Course course = courseService.getCourseById(id);
            
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }
            
            System.out.println("Current course details:");
            System.out.println("Name: " + course.getCourseName());
            System.out.println("Duration: " + course.getDuration());
            
            System.out.print("Enter new course name (leave blank to keep current): ");
            String newCourseName = scanner.nextLine();
            
            System.out.print("Enter new duration (leave blank to keep current): ");
            String newDuration = scanner.nextLine();
            
            if (courseService.updateCourse(id, newCourseName, newDuration)) {
                System.out.println("Course updated successfully!");
            } else {
                System.out.println("Failed to update course.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid course ID.");
        }
    }
    
    /**
     * Delete a course
     */
    private void deleteCourse() {
        System.out.println("\n--- DELETE COURSE ---");
        System.out.print("Enter course ID to delete: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Course course = courseService.getCourseById(id);
            
            if (course == null) {
                System.out.println("Course not found.");
                return;
            }
            
            System.out.println("Course to delete:");
            System.out.println("ID: " + course.getId());
            System.out.println("Name: " + course.getCourseName());
            System.out.println("Duration: " + course.getDuration());
            
            System.out.print("Are you sure you want to delete this course? (y/N): ");
            String confirmation = scanner.nextLine().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                if (courseService.deleteCourse(id)) {
                    System.out.println("Course deleted successfully!");
                } else {
                    System.out.println("Failed to delete course.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid course ID.");
        }
    }
}
