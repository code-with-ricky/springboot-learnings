package com.ricky.StudentManagementSystem.services;

import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        System.out.println("Student creation service called");
        return studentRepository.saveStudent(student);
    }
}