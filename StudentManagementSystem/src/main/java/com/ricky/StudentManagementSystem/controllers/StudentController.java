package com.ricky.StudentManagementSystem.controllers;

import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")  // this sets prefix for all endpoints inside this StudentController
public class StudentController {

    // Dependency injection of StudentService, as we need to call StudentService methods
    // for which we need bean of StudentService
    private StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student){
        // whatever payload we pass in request body in json format gets converted to java class (Student)
        // using jackson library
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }
}
