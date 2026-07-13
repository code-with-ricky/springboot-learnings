package com.ricky.StudentManagementSystem.services;

import com.ricky.StudentManagementSystem.dtos.request_dtos.CreateStudentRequestDTO;
import com.ricky.StudentManagementSystem.dtos.response_dtos.CreateStudentResponseDTO;
import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.exceptions.DuplicateResourceException;
import com.ricky.StudentManagementSystem.exceptions.ResourceNotFoundException;
import com.ricky.StudentManagementSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public CreateStudentResponseDTO createStudent(CreateStudentRequestDTO studentReqDto) {
        Student student = new Student();
        student.setName(studentReqDto.getName());
        student.setEmail(studentReqDto.getEmail());
        student.setAge(studentReqDto.getAge());
        student.setRollNo(studentReqDto.getRollNo());
        student.setSubject(studentReqDto.getSubject());
        // set default value of variable for soft delete as false for new records
        student.setDeleted(false);

        // check if student already exist or not
        if (emailExist(student)) {
            throw new DuplicateResourceException("Email address already exists");
        }

        // save the student
        Student studentResponse = studentRepository.save(student);

        // Create response and do mapping
        CreateStudentResponseDTO createStudentResponse = new CreateStudentResponseDTO();
        createStudentResponse.setId(studentResponse.getId());
        createStudentResponse.setName(studentResponse.getName());
        createStudentResponse.setEmail(studentResponse.getEmail());
        createStudentResponse.setAge(studentResponse.getAge());
        createStudentResponse.setRollNo(studentResponse.getRollNo());
        createStudentResponse.setSubject(studentResponse.getSubject());
        createStudentResponse.setMessage("Student created successfully!");

        return createStudentResponse;
    }

    // Optional meaning, data may be there or may not be there
    // it prevents null pointer exception
    public Student getStudentDetailsService(Long studentId) {
        // so now because of soft delete, method will change

        // Optional<Student> fetchedStudentResponse =
        // studentRepository.findById(studentId);

        // following is not a standard method
        // but based on name, spring data jpa creates implementation at run time
        // Optional<Student> fetchedStudentResponse =
        // studentRepository.findByIdAndIsDeletedFalse(studentId);

        // if (fetchedStudentResponse.isPresent()) {
        // return fetchedStudentResponse.get();
        // }
        // return null;

        Student fetchedStudentResponse = studentRepository
                .findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found."));

        // here we will reach if exception is not there and student with studentId is
        // found
        return fetchedStudentResponse;
    }

    public List<Student> getAllStudentService() {
        return studentRepository.findAllByIsDeletedFalse();
    }

    public Student updateStudentService(Long studentId, Student studentReq) {
        // Optional<Student> existingStudent =
        // studentRepository.findByIdAndIsDeletedFalse(studentId);
        // if (existingStudent.isEmpty()) {
        // return null;
        // }

        Student studentToSave = studentRepository
                .findByIdAndIsDeletedFalse(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found."));

        studentToSave.setName(studentReq.getName());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setDeleted(false);

        Student updatedStudent = studentRepository.save(studentToSave);
        return updatedStudent;
    }

    // Hard delete - Permanently delete from Database
    public void deleteStudentService(Long studentId) {
        // boolean studentExists = studentRepository.existsById(studentId);
        // if (!studentExists) {
        // return false;
        // }

        Student studentToBeDeleted = studentRepository
                .findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found."));

        studentRepository.delete(studentToBeDeleted);
    }

    // Soft Delete
    public void deleteStudentSoftly(Long id) {
        // Optional<Student> studentResponse =
        // studentRepository.findByIdAndIsDeletedFalse(id);
        // if (studentResponse.isEmpty()) {
        // return false;
        // }

        Student student = studentRepository
                .findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with id " + id + " not found."));

        student.setDeleted(true);
        studentRepository.save(student);
    }

    // Helper method
    private boolean emailExist(Student student) {
        return studentRepository.existsByEmail(student.getEmail());
    }
}