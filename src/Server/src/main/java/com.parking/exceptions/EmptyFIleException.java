package com.parking.exceptions;

public class EmptyFIleException extends RuntimeException {
    private static final long serialVersionUID = -6809687331185271459L;
    public EmptyFIleException(String message) {
        super(message);
    }
}
