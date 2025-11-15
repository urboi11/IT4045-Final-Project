package com.it4045.finalproject.exceptions;

public class CourseNameExistsException extends RuntimeException {
    public CourseNameExistsException(String message) {
        super(message);
    }
}
