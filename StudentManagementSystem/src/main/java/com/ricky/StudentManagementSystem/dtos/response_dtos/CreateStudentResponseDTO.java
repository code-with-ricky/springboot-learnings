package com.ricky.StudentManagementSystem.dtos.response_dtos;

public class CreateStudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private int age;
    private int rollNo;
    private String subject;
    private String message;

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}