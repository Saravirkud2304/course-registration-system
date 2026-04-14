package com.course.controller;

import com.course.model.Student;
import com.course.service.StudentService;
import java.util.List;
import java.util.Scanner;

/**
 * Student controller class handling user interactions for student operations
 */
public class StudentController {
    
    private final StudentService studentService;
    private final Scanner scanner;
    
    public StudentController() {
        this.studentService = new StudentService();
        this.scanner = new Scanner(System.in);
    }
    
    /**
     * Display student management menu
     */
    public void showStudentMenu() {
        while (true) {
            System.out.println("\n=== STUDENT MANAGEMENT ===");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by Name");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        viewAllStudents();
                        break;
                    case 3:
                        searchStudent();
                        break;
                    case 4:
                        updateStudent();
                        break;
                    case 5:
                        deleteStudent();
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
     * Add a new student
     */
    private void addStudent() {
        System.out.println("\n--- ADD NEW STUDENT ---");
        
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter student email: ");
        String email = scanner.nextLine();
        
        if (studentService.addStudent(name, email)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student. Please check the input and try again.");
        }
    }
    
    /**
     * View all students
     */
    private void viewAllStudents() {
        System.out.println("\n--- ALL STUDENTS ---");
        List<Student> students = studentService.getAllStudents();
        
        if (students.isEmpty()) {
            System.out.println("No students found.");
        } else {
            System.out.println("ID\tName\t\tEmail");
            System.out.println("----------------------------------------");
            for (Student student : students) {
                System.out.printf("%d\t%s\t\t%s%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getEmail());
            }
        }
    }
    
    /**
     * Search student by name
     */
    private void searchStudent() {
        System.out.println("\n--- SEARCH STUDENT ---");
        System.out.print("Enter student name to search: ");
        String name = scanner.nextLine();
        
        List<Student> students = studentService.searchStudentsByName(name);
        
        if (students.isEmpty()) {
            System.out.println("No students found with the given name.");
        } else {
            System.out.println("Search Results:");
            System.out.println("ID\tName\t\tEmail");
            System.out.println("----------------------------------------");
            for (Student student : students) {
                System.out.printf("%d\t%s\t\t%s%n", 
                    student.getId(), 
                    student.getName(), 
                    student.getEmail());
            }
        }
    }
    
    /**
     * Update student information
     */
    private void updateStudent() {
        System.out.println("\n--- UPDATE STUDENT ---");
        System.out.print("Enter student ID to update: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentService.getStudentById(id);
            
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            
            System.out.println("Current student details:");
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            
            System.out.print("Enter new name (leave blank to keep current): ");
            String newName = scanner.nextLine();
            
            System.out.print("Enter new email (leave blank to keep current): ");
            String newEmail = scanner.nextLine();
            
            if (studentService.updateStudent(id, newName, newEmail)) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Failed to update student.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID.");
        }
    }
    
    /**
     * Delete a student
     */
    private void deleteStudent() {
        System.out.println("\n--- DELETE STUDENT ---");
        System.out.print("Enter student ID to delete: ");
        
        try {
            int id = Integer.parseInt(scanner.nextLine());
            Student student = studentService.getStudentById(id);
            
            if (student == null) {
                System.out.println("Student not found.");
                return;
            }
            
            System.out.println("Student to delete:");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Email: " + student.getEmail());
            
            System.out.print("Are you sure you want to delete this student? (y/N): ");
            String confirmation = scanner.nextLine().toLowerCase();
            
            if (confirmation.equals("y") || confirmation.equals("yes")) {
                if (studentService.deleteStudent(id)) {
                    System.out.println("Student deleted successfully!");
                } else {
                    System.out.println("Failed to delete student.");
                }
            } else {
                System.out.println("Deletion cancelled.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid student ID.");
        }
    }
}
