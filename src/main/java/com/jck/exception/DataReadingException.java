package com.jck.exception;

public class DataReadingException extends RuntimeException {
    public DataReadingException(String message, Throwable cause) {
        super(message, cause);
    }
}