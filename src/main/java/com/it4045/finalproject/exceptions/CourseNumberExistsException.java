package com.it4045.finalproject.exceptions;

public class CourseNumberExistsException extends RuntimeException {
    public CourseNumberExistsException(String message) {
        super(message);
    }
}
