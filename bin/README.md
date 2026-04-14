# 📘 Course Registration System (Java Maven Project)

## 📌 Project Overview

The **Course Registration System** is a console-based Java application developed using Maven. It is designed to manage student course registrations efficiently. The system allows administrators and users to perform operations such as adding students, managing courses, and registering students into courses.

This project follows a **layered architecture** including Controller, Service, Repository, and Model, ensuring clean code organization and maintainability.

---

## 🎯 Objectives

* To automate the process of course registration
* To manage student and course data efficiently
* To implement CRUD operations using Java and JDBC
* To understand Maven project structure and dependency management

---

## 🛠️ Technologies Used

* Java (Core Java)
* MySQL Database
* JDBC (Java Database Connectivity)
* Maven (Project Management Tool)
* Console-based UI (Scanner for input/output)

---

## 🏗️ Project Architecture

The project follows a **3-tier layered architecture**:

* **Controller Layer** → Handles user input and interaction
* **Service Layer** → Contains business logic
* **Repository Layer** → Handles database operations
* **Model Layer** → Represents data objects

Flow:
User → Controller → Service → Repository → Database

---

## 📦 Modules

1. Database Design
2. Student Management Module
3. Course Management Module
4. Course Registration Module
5. View & Search Module

---

## 🗂️ Database Design

### Database Name: `course_db` 

#### Tables:

**students**

* id (INT, Primary Key, Auto Increment)
* name (VARCHAR)
* email (VARCHAR)

**courses**

* id (INT, Primary Key, Auto Increment)
* course_name (VARCHAR)
* duration (VARCHAR)

**registrations**

* id (INT, Primary Key, Auto Increment)
* student_id (INT, Foreign Key)
* course_id (INT, Foreign Key)

---

## ⚙️ Key Features

* Add, update, delete students
* Add and manage courses
* Register students into courses
* View available courses
* Search and filter records
* Console-based interactive menu

---

## ▶️ How to Run

### Prerequisites

1. **Java Development Kit (JDK) 11 or higher**
2. **Apache Maven**
3. **MySQL Server**
4. **IDE (IntelliJ IDEA, Eclipse, or VS Code)**

### Setup Instructions

#### 1. Database Setup

1. Install MySQL Server on your system
2. Start MySQL service
3. Open MySQL command line or MySQL Workbench
4. Run the provided SQL script to create the database and tables:

```sql
-- Execute the database_setup.sql file
mysql -u root -p < database_setup.sql
```

Or manually execute the SQL commands from `database_setup.sql`

#### 2. Configure Database Connection

1. Open `src/main/java/com/course/util/DBConnection.java`
2. Update the database credentials if needed:

```java
private static final String URL = "jdbc:mysql://localhost:3306/course_db";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_password"; // Change this
```

#### 3. Build and Run the Project

**Using Maven Command Line:**

1. Navigate to the project root directory
2. Compile the project:
   ```bash
   mvn clean compile
   ```
3. Run the application:
   ```bash
   mvn exec:java -Dexec.mainClass="com.course.MainApp"
   ```

**Using IDE:**

1. Import the project as a Maven project
2. Wait for Maven to download dependencies
3. Right-click on `MainApp.java` and select "Run"

---

## 📋 Usage Guide

### Main Menu Options

1. **Student Management**
   - Add new students
   - View all students
   - Search students by name
   - Update student information
   - Delete students

2. **Course Management**
   - Add new courses
   - View all courses
   - Search courses by name
   - Update course information
   - Delete courses

3. **Registration Management**
   - Register students for courses
   - View all registrations
   - View registrations by student
   - View registrations by course
   - Cancel registrations

4. **Exit**
   - Terminate the application

---

## 🚀 Future Enhancements

* GUI using Java Swing or JavaFX
* Web-based version using Spring Boot
* Role-based authentication
* Advanced reporting system
* Email notifications for registrations
* Course scheduling and timetables
* Grade management system

---

## 📁 Project Structure

```
Course_Registration_System/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── course/
│                   ├── controller/
│                   │   ├── CourseController.java
│                   │   ├── RegistrationController.java
│                   │   └── StudentController.java
│                   ├── model/
│                   │   ├── Course.java
│                   │   ├── Registration.java
│                   │   └── Student.java
│                   ├── repository/
│                   │   ├── CourseRepository.java
│                   │   ├── RegistrationRepository.java
│                   │   └── StudentRepository.java
│                   ├── service/
│                   │   ├── CourseService.java
│                   │   ├── RegistrationService.java
│                   │   └── StudentService.java
│                   ├── util/
│                   │   └── DBConnection.java
│                   └── MainApp.java
├── database_setup.sql
├── pom.xml
└── README.md
```

---

## 🔧 Troubleshooting

### Common Issues

1. **Database Connection Error**
   - Ensure MySQL server is running
   - Verify database credentials in `DBConnection.java`
   - Check if database `course_db` exists

2. **Maven Build Issues**
   - Ensure Maven is properly installed
   - Check internet connection for dependency downloads
   - Run `mvn clean install` to resolve issues

3. **Class Not Found Error**
   - Ensure MySQL JDBC driver is properly included
   - Check if `pom.xml` has the correct dependencies

---

## 📌 Conclusion

This project demonstrates how Java can be used with JDBC and Maven to build a structured, real-world application. It enhances understanding of database connectivity, layered architecture, and modular programming.

---

## 📞 Support

For any issues or questions, please refer to the troubleshooting section or check the console output for specific error messages.
