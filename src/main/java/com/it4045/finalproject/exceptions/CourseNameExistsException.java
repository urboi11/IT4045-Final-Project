package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when attempting to create a course with a name attribute that duplicates or conflicts with an existing course. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class CourseNameExistsException extends RuntimeException {
    public CourseNameExistsException(String message) {
        super(message);
    }
}
