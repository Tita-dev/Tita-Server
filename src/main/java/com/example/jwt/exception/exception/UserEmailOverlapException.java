package com.example.jwt.exception.exception;

public class UserEmailOverlapException extends RuntimeException {
    public UserEmailOverlapException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserEmailOverlapException(String msg) {
        super(msg);
    }

    public UserEmailOverlapException() {
        super();
    }
}
