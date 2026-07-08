package com.ricky.StudentManagementSystem.services;

import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    @Autowired
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    // Optional meaning, data may be there or may not be there
    // it prevents null pointer exception
    public Student getStudentDetailsService(Long studentId){
        Optional<Student> fetchedStudentResponse = studentRepository.findById(studentId);
        if(fetchedStudentResponse.isPresent()){
            return fetchedStudentResponse.get();
        }
        return null;
    }

    public List<Student> getAllStudentService() {
        return studentRepository.findAll();
    }

    public Student updateStudentService(Long studentId, Student studentReq){
        Optional<Student> existingStudent = studentRepository.findById(studentId);
        if(existingStudent.isEmpty()){
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());

        Student updatedStudent = studentRepository.save(studentToSave);
        return updatedStudent;
    }

    public Boolean deleteStudentService(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if(!studentExists){
            return false;
        }

        studentRepository.deleteById(studentId);
        return true;
    }
}