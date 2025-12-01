package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when attempting to create an account that duplicates or conflicts with an existing account. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class AccountExistsException extends RuntimeException{
    public AccountExistsException(String message) {
        super(message);
    }
}
