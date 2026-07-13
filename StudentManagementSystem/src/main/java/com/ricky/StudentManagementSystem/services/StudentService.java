package com.ricky.StudentManagementSystem.services;

import com.ricky.StudentManagementSystem.dtos.request_dtos.CreateStudentRequestDTO;
import com.ricky.StudentManagementSystem.dtos.response_dtos.CreateStudentResponseDTO;
import com.ricky.StudentManagementSystem.entities.Student;
import com.ricky.StudentManagementSystem.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Student> fetchedStudentResponse = studentRepository.findByIdAndIsDeletedFalse(studentId);

        if (fetchedStudentResponse.isPresent()) {
            return fetchedStudentResponse.get();
        }
        return null;
    }

    public List<Student> getAllStudentService() {
        return studentRepository.findAllByIsDeletedFalse();
    }

    public Student updateStudentService(Long studentId, Student studentReq) {
        Optional<Student> existingStudent = studentRepository.findByIdAndIsDeletedFalse(studentId);
        if (existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

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
    public Boolean deleteStudentService(Long studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            return false;
        }

        studentRepository.deleteById(studentId);
        return true;
    }

    // Soft Delete
    public Boolean deleteStudentSoftly(Long id) {
        Optional<Student> studentResponse = studentRepository.findByIdAndIsDeletedFalse(id);
        if (studentResponse.isEmpty()) {
            return false;
        }

        Student student = studentResponse.get();
        student.setDeleted(true);
        studentRepository.save(student);

        return true;
    }
}