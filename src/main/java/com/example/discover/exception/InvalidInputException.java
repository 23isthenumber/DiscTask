package com.example.discover.exception;

public class InvalidInputException extends RuntimeException {

    private static final String ERROR_MESSAGE = "The input is invalid";

    public InvalidInputException() {
        super(ERROR_MESSAGE);
    }
}
