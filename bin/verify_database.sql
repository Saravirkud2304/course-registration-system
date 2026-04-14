-- Verification script to check if database setup was successful
USE course_db;

-- Show all tables
SHOW TABLES;

-- Show student data
SELECT * FROM students;

-- Show course data  
SELECT * FROM courses;

-- Show registration data
SELECT * FROM registrations;

-- Show registration details with names
SELECT 
    r.id,
    s.name as student_name,
    c.course_name,
    r.registration_date
FROM registrations r
JOIN students s ON r.student_id = s.id
JOIN courses c ON r.course_id = c.id;
