package com.example.discover.exception;

public class NoMatchingPrefixException extends RuntimeException {

    private static final String ERROR_MESSAGE = "No matching prefix";

    public NoMatchingPrefixException() {
        super(ERROR_MESSAGE);
    }
}
