package com.itsj.studentmanager.exception;

public class StudentAlreadyExistedException extends RuntimeException{
    public StudentAlreadyExistedException(String message) {
        super(message);
    }
}
