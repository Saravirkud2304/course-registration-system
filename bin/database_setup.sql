-- Course Registration System Database Setup Script
-- This script creates the database and tables required for the Course Registration System

-- Create database if it doesn't exist
CREATE DATABASE IF NOT EXISTS course_db;

-- Use the database
USE course_db;

-- Create students table
CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create courses table
CREATE TABLE IF NOT EXISTS courses (
    id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(100) NOT NULL UNIQUE,
    duration VARCHAR(50) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create registrations table
CREATE TABLE IF NOT EXISTS registrations (
    id INT AUTO_INCREMENT PRIMARY KEY,
    student_id INT NOT NULL,
    course_id INT NOT NULL,
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (student_id) REFERENCES students(id) ON DELETE CASCADE,
    FOREIGN KEY (course_id) REFERENCES courses(id) ON DELETE CASCADE,
    UNIQUE KEY unique_registration (student_id, course_id)
);

-- Insert sample data for testing
INSERT IGNORE INTO students (name, email) VALUES
('John Doe', 'john.doe@email.com'),
('Jane Smith', 'jane.smith@email.com'),
('Mike Johnson', 'mike.johnson@email.com'),
('Sarah Williams', 'sarah.williams@email.com'),
('David Brown', 'david.brown@email.com');

INSERT IGNORE INTO courses (course_name, duration) VALUES
('Java Programming', '3 months'),
('Web Development', '4 months'),
('Database Management', '2 months'),
('Software Engineering', '6 months'),
('Data Structures', '3 months');

-- Insert sample registrations
INSERT IGNORE INTO registrations (student_id, course_id) VALUES
(1, 1), -- John Doe - Java Programming
(1, 3), -- John Doe - Database Management
(2, 2), -- Jane Smith - Web Development
(3, 1), -- Mike Johnson - Java Programming
(3, 4), -- Mike Johnson - Software Engineering
(4, 2), -- Sarah Williams - Web Development
(4, 5), -- Sarah Williams - Data Structures
(5, 3); -- David Brown - Database Management

-- Display success message
SELECT 'Database setup completed successfully!' as message;
