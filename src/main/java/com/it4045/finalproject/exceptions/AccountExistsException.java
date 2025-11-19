package com.it4045.finalproject.exceptions;

public class AccountExistsException extends RuntimeException{
    public AccountExistsException(String message) {
        super(message);
    }
}
