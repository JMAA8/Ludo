package com.ludoo.exceptions;

public class InvalidGameException extends Exception{

    private String message;

    public InvalidGameException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
