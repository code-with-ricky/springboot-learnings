package com.ricky.StudentManagementSystem.repositories;

import com.ricky.StudentManagementSystem.entities.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    public Student saveStudent(Student student) {
        System.out.println("Student Repository save student called!");
        return student;
    }
}
