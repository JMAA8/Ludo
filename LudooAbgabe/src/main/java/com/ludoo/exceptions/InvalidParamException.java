package com.ludoo.exceptions;

public class InvalidParamException extends Exception{

    private String message;

    public InvalidParamException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
