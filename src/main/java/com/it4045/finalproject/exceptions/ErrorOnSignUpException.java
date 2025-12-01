package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when an error occurs during the sign-up process. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class ErrorOnSignUpException extends RuntimeException {
    public ErrorOnSignUpException(String message) {
        super(message);
    }
    
}
