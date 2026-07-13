package com.ricky.StudentManagementSystem.exceptions;

public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message){
        super(message);
    }
}
