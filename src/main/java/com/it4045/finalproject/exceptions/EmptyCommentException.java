package com.it4045.finalproject.exceptions;

/**
 * A custom exception thrown when attempting to create a comment that lacks any actual content. Can carry a message in the form of a string.
 * @author Enterprise Application Development Final Project Group
 */
public class EmptyCommentException extends RuntimeException {
    public EmptyCommentException(String message) {
        super(message);
    }
}
