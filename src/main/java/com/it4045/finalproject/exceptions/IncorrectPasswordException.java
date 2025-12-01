package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when an incorrect password is provided during authentication or password validation processes. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException (String message) {
        super(message);
    }
    
}
