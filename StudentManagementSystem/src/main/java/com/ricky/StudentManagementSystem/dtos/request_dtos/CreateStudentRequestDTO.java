package com.ricky.StudentManagementSystem.dtos.request_dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateStudentRequestDTO {
    // validations
    @NotBlank(message = "Name not provided.")  // cannot be null, empty or white spaces
    @Size(min = 2, max = 50, message = "Name should be between 2 to 50 characters long.")
    private String name;

    @NotBlank(message = "Email not provided.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Age not provided.")
    @Min(value = 18, message = "Student must be atleast 18 years old.")
    private Integer age;

    @NotNull(message = "Roll Number not provided.")
    private Integer rollNo;

    @NotBlank(message = "Subject not provided.")
    private String subject;

    // GETTERS
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public Integer getAge() {
        return age;
    }
    public Integer getRollNo() {
        return rollNo;
    }
    public String getSubject() {
        return subject;
    }
}
