package com.ricky.StudentManagementSystem.repositories;
import com.ricky.StudentManagementSystem.entities.Student;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByIdAndIsDeletedFalse(Long studentId);

    List<Student> findAllByIsDeletedFalse();

    boolean existsByEmail(String email);
}