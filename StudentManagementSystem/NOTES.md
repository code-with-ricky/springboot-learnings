# Student Management System - Basic CRUD Application
- This is a basic crud application
- following is the architecture we are going to follow:

![Client-Server-Database](images/c-s-d.png)

## Spring Boot Application configurations:
- **`Project:`** Maven
- **`Version:`** v4.1.0
- **`Project Metadata:`**
    - **`Group:`** com.ricky
    - **`Artifact:`** StudentManagementSystem
    - **`Package Name:`** com.ricky.StudentManagementSystem
- **`Packaging:`** Jar
- **`Configuration:`** Properties
- **`Java version:`** 17

## Dependencies required:
- Spring Web
- Spring Data Jpa
- MySQL Driver

## Steps for each operation
1. Listen to API endpoint
2. Perform Business Logic
3. Interact with DB
4. Return response back to client

> But we will not all in one file, we will separate out the responsibilities into different files

![Code File Structure](images/CodeStructure.png)