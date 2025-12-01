package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when attempting to access an account that does not exist. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class AccountDoesNotExistException extends RuntimeException {
    public AccountDoesNotExistException(String message) {
        super(message);
    }
}
