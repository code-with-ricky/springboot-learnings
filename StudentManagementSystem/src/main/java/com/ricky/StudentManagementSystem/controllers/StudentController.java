package com.ricky.StudentManagementSystem.controllers;

import com.ricky.StudentManagementSystem.dtos.request_dtos.CreateStudentRequestDTO;
import com.ricky.StudentManagementSystem.dtos.response_dtos.CreateStudentResponseDTO;
import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.services.StudentService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/students")  // this sets prefix for all endpoints inside this StudentController
public class StudentController {

    // Dependency injection of StudentService, as we need to call StudentService methods
    // for which we need bean of StudentService
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    // POST /api/students
    @PostMapping
    public ResponseEntity<CreateStudentResponseDTO> createStudent(
        @Valid @RequestBody CreateStudentRequestDTO studentReqDto
    ){
        // whatever payload we pass in request body in json format gets converted to java class (Student)
        // using jackson library
        CreateStudentResponseDTO createdStudent = studentService.createStudent(studentReqDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    // GET /api/students/{id}
    // Example: /api/students/1
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentDetails(@PathVariable Long id){
        Student fetchedStudent = studentService.getStudentDetailsService(id);
        if(fetchedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(fetchedStudent);
    }

    // GET /api/students
    // Example: /api/students
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudentDetails(){
        List<Student> fetchedAllStudent = studentService.getAllStudentService();
        return ResponseEntity.ok(fetchedAllStudent);
    }

    // PUT /api/students/{id}
    // Example: /api/students/1
    @PutMapping("{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student){
        Student updatedStudent = studentService.updateStudentService(id, student);
        if(updatedStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedStudent);
    }

    // DELETE /api/students/{id}
    // Example: /api/students/1
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id){
        boolean deleteResponse = studentService.deleteStudentService(id);
        if(!deleteResponse) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    // Here we are learning about query parameters also
    // PATCH /api/students?id=2
    @PatchMapping
    public ResponseEntity<?> softDeleteStudent(@RequestParam Long id){
        Boolean isStudentDeleted = studentService.deleteStudentSoftly(id);

        if(!isStudentDeleted){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }
}
