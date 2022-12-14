package com.example.discover.exception;

public class PatternNotKnownException extends RuntimeException {

    private static final String ERROR_MESSAGE = "Pattern not known";

    public PatternNotKnownException() {
        super(ERROR_MESSAGE);
    }
}